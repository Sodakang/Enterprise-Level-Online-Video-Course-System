<template>
    <div>
        <h4 class="lighter">
            <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
            <router-link to="/business/course" class="pink"> {{course.name}} </router-link>
        </h4>
        <hr>
        <p>
            <router-link  to="/business/course" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-arrow-left blue"/>
                Back to Course
            </router-link>
            &nbsp;
            <button  v-on:click="add" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit blue"/>
                Create
            </button>
            &nbsp;
            <button  v-on:click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh green"/>
                Refresh
            </button>
        </p>
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Operations</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="chapter in chapters">
                <td>{{chapter.id}}</td>
                <td>{{chapter.name}}</td>

                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button v-on:click="toSection(chapter)" class="btn btn-white btn-xs btn-info btn-round">
                            chapter
                        </button>&nbsp;
                        <button v-on:click="edit(chapter)" class="btn btn-white btn-xs btn-info btn-round">
                            edit
                        </button>&nbsp;
                        <button v-on:click="del(chapter.id)" class="btn btn-white btn-xs btn-warning btn-round">
                            delete
                        </button>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <!-- Modal -->
        <div class="modal fade" id="form-modal" tabindex="-1" role="dialog" aria-labelledby="form-modal-label">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="form-modal-label">Create New Chapter</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">Name</label>
                                <div class="col-sm-10">
                                    <input v-model="chapter.name" type="text" class="form-control" id="name" placeholder="Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Course</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static">{{course.name}}</p>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <button v-on:click="save()" type="button" class="btn btn-primary">Save</button>
                    </div>
                </div>
            </div>
        </div>
        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"/>
    </div>
</template>

<script>
    import Pagination from "../../components/pagination";
    export default {
        components: {Pagination},
        name: "chapter",
        data: function() {
          return {
              chapter: {},
              chapters: [],
              course: {}
          }
        },
        mounted: function() {
            let _this = this;
            _this.$refs.pagination.size = 5;
            let course = SessionStorage.get("course") || {};
            if(Tool.isEmpty(course)) {
                _this.$router.push("/welcome");
            }
            _this.course = course;
            _this.list(1);
            // The method I for making the sidebar active.
            // this.$parent.activeSidebar("business-chapter-sidebar");

        },
        methods: {
            /**
             * Click "Create".
             */
            add() {
                let _this = this;
                this.chapter = {};
                $("#form-modal").modal("show");
            },

            /**
             * Click "Edit".
             * @param chapter
             */
            edit(chapter) {
                let _this = this;
                _this.chapter = $.extend({}, chapter);
                $("#form-modal").modal("show");
            },

            /**
             * List query.
             * @param page
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/list', {
                    page: page,
                    size: _this.$refs.pagination.size,
                    courseId: _this.course.id
                }).then((response) => {
                    Loading.hide();
                    // console.log("The results of searching the chapter list: ", response);
                    let resp = response.data;
                    _this.chapters = resp.content.list;
                    _this.$refs.pagination.render(page, resp.content.total);
                })
            },

            /**
             * Click "Save".
             */
            save() {
                let _this = this;
                // Check before saving.
                if(!Validator.require(_this.chapter.name, "Chapter name") ||
                !Validator.length(_this.chapter.courseId, "Course ID", 1, 8)) {
                    return;
                }
                _this.chapter.courseId = _this.course.id;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/save',
                    _this.chapter).then((response) => {
                        Loading.hide();
                        // console.log("The results of saving the chapter list: ", response);
                        let resp = response.data;
                        if(resp.success) {
                            $("#form-modal").modal("hide");
                            _this.list(1);
                            Toast.success("Save successfully!");
                        } else {
                            Toast.warning(resp.message);
                        }
                })
            },

            /**
             * Click "Delete".
             * @param id
             */
            del(id) {
                let _this = this;
                Confirm.show("You won't be able to revert this deletion, are you sure?", function () {
                    Loading.show();
                    _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/delete/' + id).then((response) => {
                        Loading.hide();
                        // console.log("The results of deleting the chapter list: ", response);
                        let resp = response.data;
                        if(resp.success) {
                            Toast.success("Delete successfully!");
                            _this.list(1);
                        }
                    });
                });
            }

            /**
             * Click "Chapter"
             */
            toSection(chapter) {
                let _this = this;
                SessionStorage.set("chapter", chapter);
                _this.$router.push("/business/section");
            }
        }
    }
</script>