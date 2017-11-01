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
            url: ctx + '/pay/listData',
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

});