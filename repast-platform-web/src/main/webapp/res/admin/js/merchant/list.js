layui.config({
    base: ctx + '/res/admin/plugins/layui/modules/'
});
layui.use(['icheck', 'laypage', 'layer', 'reuse', 'form'], function () {
    var $ = layui.jquery,
        laypage = layui.laypage,
        pageFlag = false,
        currPage = 1;
    layer = parent.layer === undefined ? layui.layer : parent.layer,
        reuse = layui.reuse,
        form = layui.form();

    form.render('select');

    function loadList(page, pageSize) {
        var loading = layer.load();
        $.ajax({
            url: ctx + '/merchant/listData',
            type: 'POST',
            data: {page: page, pageSize: pageSize},
            success: function (data) {
                $("#dataList").html(data);

                layer.close(loading);

                reuse.tableCheck();

                var pages = $("#pageCount").val();
                if (!pageFlag) {
                    pageFlag = true;
                    laypage({
                        cont: 'page',
                        pages: pages, //总页数
                        groups: 5,//连续显示分页数
                        jump: function (obj, first) {
                            currPage = obj.curr;
                            if (!first) {
                                loadList(currPage, 10);
                            }
                        }
                    });
                }
            }
        });
    }

    loadList(1, 10);

    $('#add').on('click', function () {
        showForm(null);
    });

    function showForm(id) {
        var loading = layer.load();
        $.get(ctx + '/admin/merchant/info', {id: id}, function (form) {
            layer.open({
                type: 1,
                title: '商户信息表单',
                content: form,
                area: ['500px', '450px'],
                maxmin: true,
                yes: function (index) {
                    console.info(index);
                },
                success: function (layero, index) {
                    layer.close(loading);
                },
                end: function () {
                    loadList(currPage, 10);
                },
                full: function (elem) {
                    var win = window.top === window.self ? window : parent.window;
                    $(win).on('resize', function () {
                        var $this = $(this);
                        elem.width($this.width()).height($this.height()).css({
                            top: 0,
                            left: 0
                        });
                        elem.children('div.layui-layer-content').height($this.height() - 95);
                    });
                }
            });
        });
    }




});