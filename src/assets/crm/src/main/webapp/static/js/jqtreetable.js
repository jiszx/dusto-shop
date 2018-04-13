/**
 * Created by Administrator on 2016/7/7.
 */
(function (JQ) {

    //JQ.fn.extend({

    JQ.fn.JQAjaxTreeTable = function (Options) {

        var _thisTable = JQ(this);
        /*
         * Options的属性 Options.dataPrimaryKey //数据的主键名 Options.fnFormatData
         * //格式化数据显示效果的方法 Options.addImgSrc //+号图片的地址 Options.subImgSrc
         * //-号图片的地址
         */

        Options.dataPrimaryKey = Options.dataPrimaryKey || "id";
        Options.addImgSrc = Options.addImgSrc || "/images/p.gif";
        Options.subImgSrc = Options.subImgSrc || "/images/s.gif";
        Options.sendToServerData = {};
        /**
         * 格式化tr内容的方法 data 是后台传输过来的参数
         */
        Options.fnFormatData = Options.fnFormatData
            || function (itemdata, index, formatMethod) {
                for (var p in itemdata) {
                    s += formatMethod("<td>{0}<td>", itemdata[p]);
                }
                return s;
            };

        // 渲染子节点
        function RenderChild(responseText) {

            var $thisTR = this.$tr;

            var CurrentRowData = this.CurrentRowData; // 被点击行的数据,通过ajax的context传递过来

            var CurrentRowId = CurrentRowData[Options.dataPrimaryKey]; // 被点击行的主键值

            var parendids = $thisTR.attr("parentid") || "_p0"; // 被点击行的父辈
            var re = /\_p/ig; // 创建正则表达式模式。
            var r = parendids.match(re); // 尝试去匹配搜索字符串。
            var level = r.size(); // 总共有几个_p 也就是有几级
            var result = eval("( " + responseText + ")"); // 把客户端返回的字符串解析一下.

            var $treeimg = $thisTR.find("img.treeimg");

            //判断是 -号图片就直接收缩起来.
            if ($treeimg.attr("src") == Options.subImgSrc) {
                //收缩操作,找到所有的字系列
                $treeimg.attr("src", Options.addImgSrc);

                var childtr = _thisTable.find("tr[parentid*='_p" + CurrentRowId + "_p']");
                childtr.remove();

            } else {
                // 展开操作
                $treeimg.attr("src", Options.subImgSrc);

                for (var i = 0; i < result.length; i++) {
                    var rowdata = result[i];

                    if (isempty(rowdata)) continue;

                    var newtr = format("<tr parentid='{0}_p{1}' ></tr>",
                        parendids,
                        rowdata[Options.dataPrimaryKey]);

                    newtr = JQ(newtr);
                    newtr.data(rowdata); // 把数据绑定到tr上
                    var s = format("<td>" +
                        "<span style='width:{1}px;display: inline-block;text-align: right;'><img alt='' class='treeimg'  src='{0}' /></span>" +
                        "</td>", Options.addImgSrc, level * 20);

                    s += Options.fnFormatData(rowdata, i, format);

                    newtr.append(s);
                    $thisTR.after(newtr);

                    // 给新增加的行添加上单击事件处理方法
                    newtr.find("img.treeimg").click(onImgClick);
                }


            }

        }

        // 使用示例
        // var template = "{0}欢迎你在{1}上给{0}留言，交流看法";
        // var author = "晴枫";
        // var site = "枫芸志";
        // var msg = String.format(template, author, site);
        function format(src) {
            if (arguments.length == 0)
                return null;
            var args = Array.prototype.slice.call(arguments, 1);
            return src.replace(/\{(\d+)\}/g, function (m, i) {
                return args[i];
            });
        };

        function isempty(obj) {
            if (obj == null || obj == "" || obj == undefined) {
                return true;
            }

            for (var p in obj) {
                return false; //有属性就是false
            }
            return true;
        }

        // 当+号被点击时触发
        function onImgClick() {

            // this 指向的是当前被点击的+号图片
            var $thisTR = JQ(this).parents("tr");

            var CurrentRowData = $thisTR.data();

            if (isempty(CurrentRowData)) {
                CurrentRowData = { dataId: $thisTR.attr("dataid") };
            } else {
                CurrentRowData.dataId = CurrentRowData.Id || CurrentRowData.dataId;
            }



            // 加个参数OptionMethod=GetChildrenTrData一起传到后台
            CurrentRowData.OptionMethod = "GetChildrenTrData";

            //每次都传递到后台的参数
            for (var p in Options.sendToServerData) {
                CurrentRowData[p] = Options.sendToServerData[p];
            }

            var d = {
                type: "post",
                context: {
                    "$tr": $thisTR,
                    "CurrentRowData": CurrentRowData
                }, // 把当前点击的Tr 传递到ajax回调函数中
                data: CurrentRowData,
                success: RenderChild
            };
            if (Options.url) {
                d.url = Options.url;
            }
            JQ.ajax(d);

        }



        function fristLoad() {
            var $fristtr = _thisTable.find("tr:first");
            var CurrentRowData = {};
            CurrentRowData.OptionMethod = "GetChildrenTrData";
            CurrentRowData.dataId = 0;
            var d = {
                type: "post",
                context: {
                    "$tr": $fristtr,
                    "CurrentRowData": CurrentRowData
                }, // 把当前点击的Tr 传递到ajax回调函数中
                data: CurrentRowData,
                success: RenderChild
            };
            if (Options.url) {
                d.url = Options.url;
            }

            JQ.ajax(d);
        }


        fristLoad();

        _thisTable.find("tr img.treeimg").click(onImgClick);

    }

    //   });

})(jQuery || JQ || $);