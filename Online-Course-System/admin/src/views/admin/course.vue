<template>
    <div>
        <p>
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

        <div class="row">
            <div v-for="course in courses" class="col-md-4">
                <div class="thumbnail search-thumbnail">
                    <img v-show="!course.image" class="media-object" src="/static/image/demo-course.jpg" />
                    <img v-show="course.image" class="media-object" v-bind:src="course.image" />
                    <div class="caption">
                        <div class="clearfix">
                            <span class="pull-right label label-primary info-label">
                                {{COURSE_LEVEL | optionKV(course.level)}}
                            </span>
                            <span class="pull-right label label-primary info-label">
                                {{COURSE_CHARGE | optionKV(course.charge)}}
                            </span>
                            <span class="pull-right label label-primary info-label">
                                {{COURSE_STATUS | optionKV(course.status)}}
                            </span>
                        </div>

                        <h3 class="search-title">
                            <a href="#" class="blue">{{course.name}}</a>
                        </h3>
                        <p>
                            <span class="blue bolder bigger-150">{{course.price}}&nbsp;<i class="fa fa-dollar"></i></span>&nbsp;
                        </p>
                        <p>{{course.summary}}</p>
                        <p>
                            <span class="badge badge-info">{{course.id}}</span>
                            <span class="badge badge-info">Order: {{course.sort}}</span>
                            <span class="badge badge-info">{{course.duration}}</span>
                        </p>
                        <p>
                            <button v-on:click="toChapter(course)" class="btn btn-white btn-xs btn-info btn-round">
                                Chapter
                            </button>&nbsp;
                            <button v-on:click="edit(course)" class="btn btn-white btn-xs btn-info btn-round">
                                Edit
                            </button>&nbsp;
                            <button v-on:click="del(course.id)" class="btn btn-white btn-xs btn-warning btn-round">
                                Delete
                            </button>
                        </p>
                    </div>
                </div>
            </div>
        </div>

<!--        <table id="simple-table" class="table  table-bordered table-hover">-->
<!--            <thead>-->
<!--            <tr>-->
<!--                <th>ID</th>-->
<!--                <th>Name</th>-->
<!--                <th>Summary</th>-->
<!--                <th>Duration (seconds)</th>-->
<!--                <th>Price ($)</th>-->
<!--                <th>Cover Image</th>-->
<!--                <th>Level</th>-->
<!--                <th>Charge or Free</th>-->
<!--                <th>Status</th>-->
<!--                <th>Enrollment</th>-->
<!--                <th>Order</th>-->
<!--                <th>Operations</th>-->
<!--            </tr>-->
<!--            </thead>-->

<!--            <tbody>-->
<!--            <tr v-for="course in courses">-->
<!--                <td>{{course.id}}</td>-->
<!--                <td>{{course.name}}</td>-->
<!--                <td>{{course.summary}}</td>-->
<!--                <td>{{course.duration}}</td>-->
<!--                <td>{{course.price}}</td>-->
<!--                <td>{{course.image}}</td>-->
<!--                <td>{{COURSE_LEVEL | optionKV(course.level)}}</td>-->
<!--                <td>{{COURSE_CHARGE | optionKV(course.charge)}}</td>-->
<!--                <td>{{COURSE_STATUS | optionKV(course.status)}}</td>-->
<!--                <td>{{course.enrollment}}</td>-->
<!--                <td>{{course.sort}}</td>-->
<!--                <td>-->
<!--                    <div class="hidden-sm hidden-xs btn-group">-->
<!--                        <button v-on:click="edit(course)" class="btn btn-xs btn-info">-->
<!--                            <i class="ace-icon fa fa-pencil bigger-120"></i>-->
<!--                        </button>-->
<!--                        <button v-on:click="del(course.id)" class="btn btn-xs btn-danger">-->
<!--                            <i class="ace-icon fa fa-trash-o bigger-120"></i>-->
<!--                        </button>-->
<!--                    </div>-->
<!--                </td>-->
<!--            </tr>-->
<!--            </tbody>-->
<!--        </table>-->
        <!-- Modal -->
        <div class="modal fade" id="form-modal" tabindex="-1" role="dialog" aria-labelledby="form-modal-label">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="form-modal-label">Create New Course</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Name</label>
                                <div class="col-sm-10">
                                    <input v-model="course.name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Summary</label>
                                <div class="col-sm-10">
                                    <input v-model="course.summary" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Duration (seconds)</label>
                                <div class="col-sm-10">
                                    <input v-model="course.duration" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Price ($)</label>
                                <div class="col-sm-10">
                                    <input v-model="course.price" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Cover Image</label>
                                <div class="col-sm-10">
                                    <input v-model="course.image" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Level</label>
                                <div class="col-sm-10">
                                    <select v-model="course.level" class="form-control">
                                        <option v-for="o in COURSE_LEVEL" v-bind:value="o.key">{{o.value}}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Charge or Free</label>
                                <div class="col-sm-10">
                                    <select v-model="course.charge" class="form-control">
                                        <option v-for="o in COURSE_CHARGE" v-bind:value="o.key">{{o.value}}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Status</label>
                                <div class="col-sm-10">
                                    <select v-model="course.status" class="form-control">
                                        <option v-for="o in COURSE_STATUS" v-bind:value="o.key">{{o.value}}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Enrollment</label>
                                <div class="col-sm-10">
                                    <input v-model="course.enrollment" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Order</label>
                                <div class="col-sm-10">
                                    <input v-model="course.sort" class="form-control">
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
        name: "business-course",
        data: function() {
          return {
              course: {},
              courses: [],
              COURSE_LEVEL: COURSE_LEVEL,
              COURSE_CHARGE: COURSE_CHARGE,
              COURSE_STATUS: COURSE_STATUS
          }
        },
        mounted: function() {
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.list(1);
            // The method I for making the sidebar active.
            // this.$parent.activeSidebar("business-course-sidebar");

        },
        methods: {
            /**
             * Click "Create".
             */
            add() {
                let _this = this;
                this.course = {};
                $("#form-modal").modal("show");
            },

            /**
             * Click "Edit".
             * @param course
             */
            edit(course) {
                let _this = this;
                _this.course = $.extend({}, course);
                $("#form-modal").modal("show");
            },

            /**
             * List query.
             * @param page
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/list', {
                    page: page,
                    size: _this.$refs.pagination.size
                }).then((response) => {
                    Loading.hide();
                    // console.log("The results of searching the course list: ", response);
                    let resp = response.data;
                    _this.courses = resp.content.list;
                    _this.$refs.pagination.render(page, resp.content.total);
                })
            },

            /**
             * Click "Save".
             */
            save() {
                let _this = this;
                // Check before saving.
                if (1 != 1
                    || !Validator.require(_this.course.name, "Name")
                    || !Validator.length(_this.course.name, "Name", 1, 50)
                    || !Validator.length(_this.course.summary, "Summary", 1, 2000)
                    || !Validator.length(_this.course.image, "Cover Image", 1, 100)
                ) {
                    return;
                }
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/save',
                    _this.course).then((response) => {
                        Loading.hide();
                        // console.log("The results of saving the course list: ", response);
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
                    _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/delete/' + id).then((response) => {
                        Loading.hide();
                        // console.log("The results of deleting the course list: ", response);
                        let resp = response.data;
                        if(resp.success) {
                            Toast.success("Delete successfully!");
                            _this.list(1);
                        }
                    });
                });
            },

            /**
             * Click "Chapter".
             * @param course
             */
            toChapter(course) {
                let _this = this;
                SessionStorage.set("course", course);
                _this.$router.push("/business/chapter");
            }
        }
    }
</script>

<style scoped>
    .caption h3 {
        font-size: 20px;
    }
</style>