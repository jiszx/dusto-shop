/**
 * Created by Administrator on 2016/7/7.
 */
(function (JQ) {

    //JQ.fn.extend({

    JQ.fn.JQAjaxTreeTable = function (Options) {

        var _thisTable = JQ(this);
        /*
         * Options������ Options.dataPrimaryKey //���ݵ������� Options.fnFormatData
         * //��ʽ��������ʾЧ���ķ��� Options.addImgSrc //+��ͼƬ�ĵ�ַ Options.subImgSrc
         * //-��ͼƬ�ĵ�ַ
         */

        Options.dataPrimaryKey = Options.dataPrimaryKey || "id";
        Options.addImgSrc = Options.addImgSrc || "/images/p.gif";
        Options.subImgSrc = Options.subImgSrc || "/images/s.gif";
        Options.sendToServerData = {};
        /**
         * ��ʽ��tr���ݵķ��� data �Ǻ�̨��������Ĳ���
         */
        Options.fnFormatData = Options.fnFormatData
            || function (itemdata, index, formatMethod) {
                for (var p in itemdata) {
                    s += formatMethod("<td>{0}<td>", itemdata[p]);
                }
                return s;
            };

        // ��Ⱦ�ӽڵ�
        function RenderChild(responseText) {

            var $thisTR = this.$tr;

            var CurrentRowData = this.CurrentRowData; // ������е�����,ͨ��ajax��context���ݹ���

            var CurrentRowId = CurrentRowData[Options.dataPrimaryKey]; // ������е�����ֵ

            var parendids = $thisTR.attr("parentid") || "_p0"; // ������еĸ���
            var re = /\_p/ig; // ����������ʽģʽ��
            var r = parendids.match(re); // ����ȥƥ�������ַ�����
            var level = r.size(); // �ܹ��м���_p Ҳ�����м���
            var result = eval("( " + responseText + ")"); // �ѿͻ��˷��ص��ַ�������һ��.

            var $treeimg = $thisTR.find("img.treeimg");

            //�ж��� -��ͼƬ��ֱ����������.
            if ($treeimg.attr("src") == Options.subImgSrc) {
                //��������,�ҵ����е���ϵ��
                $treeimg.attr("src", Options.addImgSrc);

                var childtr = _thisTable.find("tr[parentid*='_p" + CurrentRowId + "_p']");
                childtr.remove();

            } else {
                // չ������
                $treeimg.attr("src", Options.subImgSrc);

                for (var i = 0; i < result.length; i++) {
                    var rowdata = result[i];

                    if (isempty(rowdata)) continue;

                    var newtr = format("<tr parentid='{0}_p{1}' ></tr>",
                        parendids,
                        rowdata[Options.dataPrimaryKey]);

                    newtr = JQ(newtr);
                    newtr.data(rowdata); // �����ݰ󶨵�tr��
                    var s = format("<td>" +
                        "<span style='width:{1}px;display: inline-block;text-align: right;'><img alt='' class='treeimg'  src='{0}' /></span>" +
                        "</td>", Options.addImgSrc, level * 20);

                    s += Options.fnFormatData(rowdata, i, format);

                    newtr.append(s);
                    $thisTR.after(newtr);

                    // �������ӵ�������ϵ����¼�������
                    newtr.find("img.treeimg").click(onImgClick);
                }


            }

        }

        // ʹ��ʾ��
        // var template = "{0}��ӭ����{1}�ϸ�{0}���ԣ���������";
        // var author = "���";
        // var site = "��ܿ־";
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
                return false; //�����Ծ���false
            }
            return true;
        }

        // ��+�ű����ʱ����
        function onImgClick() {

            // this ָ����ǵ�ǰ�������+��ͼƬ
            var $thisTR = JQ(this).parents("tr");

            var CurrentRowData = $thisTR.data();

            if (isempty(CurrentRowData)) {
                CurrentRowData = { dataId: $thisTR.attr("dataid") };
            } else {
                CurrentRowData.dataId = CurrentRowData.Id || CurrentRowData.dataId;
            }



            // �Ӹ�����OptionMethod=GetChildrenTrDataһ�𴫵���̨
            CurrentRowData.OptionMethod = "GetChildrenTrData";

            //ÿ�ζ����ݵ���̨�Ĳ���
            for (var p in Options.sendToServerData) {
                CurrentRowData[p] = Options.sendToServerData[p];
            }

            var d = {
                type: "post",
                context: {
                    "$tr": $thisTR,
                    "CurrentRowData": CurrentRowData
                }, // �ѵ�ǰ�����Tr ���ݵ�ajax�ص�������
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
                }, // �ѵ�ǰ�����Tr ���ݵ�ajax�ص�������
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