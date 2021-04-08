package ArticleManage;

/**
 * @author 王艺博
 * @date 2021/4/8 8:10
 */
public class Article {
    // 名字 单价 库存 售出数量
    public String name;
    public double price;
    public int amount;
    public int number;

    public void print(int index) {
        System.out.println(index + "\t  " + name + "  \t" + price + "  \t" + amount + "\t  " + number);
    }

    public void setArticle(String mingzi,double danjia,int kucun,int xiaoshou) {
        name = mingzi;
        price = danjia;
        amount = kucun;
        number = xiaoshou;
    }

}
