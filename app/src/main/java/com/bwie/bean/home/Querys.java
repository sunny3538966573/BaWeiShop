package com.bwie.bean.home;

import java.util.List;

/**
 * date: 2019/1/2.
 * Created by Administrator
 * function:
 */
public class Querys {

    /**
     * result : [{"commodityId":87,"commodityName":"秋季新款韩版女装短外套秋装春秋冬学院风休闲简约宽松拼接长袖短外套","masterPic":"http://172.17.8.100/images/small/commodity/nz/wt/2/1.jpg","price":358,"saleNum":0},{"commodityId":94,"commodityName":"秋冬新款宽松套头绣花纯色连帽加绒加厚卫衣女装外套","masterPic":"http://172.17.8.100/images/small/commodity/nz/wy/2/1.jpg","price":129,"saleNum":0},{"commodityId":89,"commodityName":"森马毛呢外套女士简约气质西装领短款呢子大衣韩版","masterPic":"http://172.17.8.100/images/small/commodity/nz/wt/4/1.jpg","price":99,"saleNum":0},{"commodityId":86,"commodityName":"韩版装冬装春秋冬小清新学院风皮毛一体翻领短款加绒加厚机车服夹克外套","masterPic":"http://172.17.8.100/images/small/commodity/nz/wt/1/1.jpg","price":199,"saleNum":0},{"commodityId":92,"commodityName":"冬季新款女款轻薄羽绒服女短款外套女纯色简约百搭保暖女上衣新品女装外套","masterPic":"http://172.17.8.100/images/small/commodity/nz/wt/7/1.jpg","price":229,"saleNum":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 87
         * commodityName : 秋季新款韩版女装短外套秋装春秋冬学院风休闲简约宽松拼接长袖短外套
         * masterPic : http://172.17.8.100/images/small/commodity/nz/wt/2/1.jpg
         * price : 358
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
