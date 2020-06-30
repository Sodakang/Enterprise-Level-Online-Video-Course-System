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
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Course ID</th>
                <th>Chapter ID</th>
                <th>Video Address</th>
                <th>Video Length (s)</th>
                <th>Charge or Not (C: Charge, F: Free)</th>
                <th>Order</th>
                <th>Create time</th>
                <th>Update time</th>
                <th>Operations</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="section in sections">
                    <td>{{section.id}}</td>
                    <td>{{section.title}}</td>
                    <td>{{section.courseId}}</td>
                    <td>{{section.chapterId}}</td>
                    <td>{{section.video}}</td>
                    <td>{{section.time}}</td>
                    <td>{{section.charge}}</td>
                    <td>{{section.sort}}</td>
                    <td>{{section.createdAt}}</td>
                    <td>{{section.updatedAt}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button v-on:click="edit(section)" class="btn btn-xs btn-info">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>
                        <button v-on:click="del(section.id)" class="btn btn-xs btn-danger">
                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
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
                        <h4 class="modal-title" id="form-modal-label">Create New Section</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">ID</label>
                                    <div class="col-sm-10">
                                        <input v-model="section.id" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Title</label>
                                    <div class="col-sm-10">
                                        <input v-model="section.title" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Course ID</label>
                                    <div class="col-sm-10">
                                        <input v-model="section.courseId" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Chapter ID</label>
                                    <div class="col-sm-10">
                                        <input v-model="section.chapterId" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Video Address</label>
                                    <div class="col-sm-10">
                                        <input v-model="section.video" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Video Length (s)</label>
                                    <div class="col-sm-10">
                                        <input v-model="section.time" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Charge or Not (C: Charge, F: Free)</label>
                                    <div class="col-sm-10">
                                        <input v-model="section.charge" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Order</label>
                                    <div class="col-sm-10">
                                        <input v-model="section.sort" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Create time</label>
                                    <div class="col-sm-10">
                                        <input v-model="section.createdAt" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Update time</label>
                                    <div class="col-sm-10">
                                        <input v-model="section.updatedAt" class="form-control">
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
        name: "section",
        data: function() {
          return {
              section: {},
              sections: []
          }
        },
        mounted: function() {
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.list(1);
            // The method I for making the sidebar active.
            // this.$parent.activeSidebar("business-section-sidebar");

        },
        methods: {
            /**
             * Click "Create".
             */
            add() {
                let _this = this;
                this.section = {};
                $("#form-modal").modal("show");
            },

            /**
             * Click "Edit".
             * @param section
             */
            edit(section) {
                let _this = this;
                _this.section = $.extend({}, section);
                $("#form-modal").modal("show");
            },

            /**
             * List query.
             * @param page
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/list', {
                    page: page,
                    size: _this.$refs.pagination.size
                }).then((response) => {
                    Loading.hide();
                    // console.log("The results of searching the section list: ", response);
                    let resp = response.data;
                    _this.sections = resp.content.list;
                    _this.$refs.pagination.render(page, resp.content.total);
                })
            },

            /**
             * Click "Save".
             */
            save() {
                let _this = this;
                // Check before saving.

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/save',
                    _this.section).then((response) => {
                        Loading.hide();
                        // console.log("The results of saving the section list: ", response);
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
                    _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/delete/' + id).then((response) => {
                        Loading.hide();
                        // console.log("The results of deleting the section list: ", response);
                        let resp = response.data;
                        if(resp.success) {
                            Toast.success("Delete successfully!");
                            _this.list(1);
                        }
                    });
                });
            }
        }
    }
</script>