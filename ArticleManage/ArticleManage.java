package ArticleManage;



import javax.management.monitor.StringMonitor;
import java.util.Scanner;

/**
 * @author 王艺博
 * @date 2021/4/8 8:24
 */
public class ArticleManage {
    ArticleSet articleSet = new ArticleSet();
    Scanner scanner = new Scanner(System.in);
    // 初始化商品
    public void initial() {
        Article xiaomi11 = new Article();
        xiaomi11.setArticle("小米11",1111,1111,11);

        Article xiaomi11Pro = new Article();
        xiaomi11Pro.setArticle("小米12",2222,2222,22);

        Article xiaomi11Ultra = new Article();
        xiaomi11Ultra.setArticle("小米13",3333,3333,33);

        articleSet.article[0] = xiaomi11;
        articleSet.article[1] = xiaomi11Pro;
        articleSet.article[2] = xiaomi11Ultra;
    }
    //  提示菜单
    public void startMneu(){
        boolean flag = true;
        do {
            System.out.println("******************************");
            System.out.println("1 查看商品信息");
            System.out.println("2 新增商品信息");
            System.out.println("3 删除商品信息");
            System.out.println("4 卖出商品信息");
            System.out.println("5 销售排行榜");
            System.out.println("6 退出");
            System.out.println("******************************");
            System.out.println("请输入你要执行的操作：");
            int funNO = scanner.nextInt();
            switch ( funNO ) {
                case 1:
                    System.out.println("查看商品信息");
                    chakan();
                    break;
                case 2:
                    System.out.println("新增商品信息");
                    add();
                    break;
                case 3:
                    System.out.println("删除商品信息");
                    delete();
                    break;
                case 4:
                    System.out.println("卖出商品");
                    sell();
                    break;
                case 5:
                    System.out.println("销售排行榜");
                    paihangbang();
                    break;
                case 6:
                    System.out.println("谢谢使用，系统即将退出！");
                    flag = false;
                    break;
            }
        }while(flag);

    }
    // 产看商品
    private void chakan() {
        System.out.println("编号 \t名字 \t   价格 \t\t 库存 \t 售出");
        for (int i = 0; i < articleSet.article.length; i++) {
            if ( articleSet.article[i] != null) {
                articleSet.article[i].print(i+1);
            }
        }
    }
    // 新增商品
    private void add() {
        /*
            定义键盘扫描器
            提示用户输入商品名称
            接收用户输入  商品名称
            提示输入价格 库存
            接收以上数据
         */
        System.out.println("请输入新增的商品名称：");
        String name = scanner.next();
        System.out.println("请输入商品价格：");
        double price = scanner.nextDouble();
        System.out.println("请输入商品库存：");
        int amount = scanner.nextInt();
        int number = 0;
        // 新建Article对象，并用 setArticle()方法传入用户输入的值
        Article addArticle = new Article();
        addArticle.setArticle(name,price,amount,number);
        // 将新输入的商品用for循环打印到商品仓库中
        for (int i = 0; i < articleSet.article.length; i++) {
            if ( articleSet.article[i] == null) {
                articleSet.article[i] = addArticle;
                break;
            }
        }
    }

    private void delete() {
        boolean flag = true;
        System.out.println("请输入要删除的商品编号：");
        int card = scanner.nextInt();
        for (int i = 0; i < articleSet.article.length; i++) {
            if ( articleSet.article[i] != null && (i+1) == card ) {
                // 删除i位置的元素（具体操作：把后面的元素向前移动，覆盖掉要删除的元素）
                int j = i;
                while ( articleSet.article[j+1] != null) {
                    articleSet.article[j] = articleSet.article[j+1];// 把后面的元素向前移动，覆盖掉要删除的元素
                    j++;
                }
                articleSet.article[j] = null;
                flag = true;
                break;
            }else {
                flag = false;
            }
        }
        if (flag) {
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败，暂无商品信息");
        }
    }

    private void sell() {
        boolean flag = true;
        System.out.println("请输入你要卖出商品的名称：");
        String name = scanner.next();
        for (int i = 0; i < articleSet.article.length; i++) {
            if ( articleSet.article[i] != null && articleSet.article[i].name.equals(name)) {
                System.out.println("请输入你要卖出的数量：");
                int number = scanner.nextInt();
                if ( number <= articleSet.article[i].amount ) {
                    articleSet.article[i].amount = articleSet.article[i].amount - number;
                    articleSet.article[i].number = articleSet.article[i].number + number;
                }
                System.out.println("卖出成功");
                break;
            }
        }
    }

    private void paihangbang() {
        /*
           升序排序

            for () 所有元素参与排序
                for () 当前元素只和后面的元素比较
                    if (当前元素 < 后面的元素) {
                        交换位置
                    }
         */
        // 排序(冒泡排序)
        for (int i = 0; i < articleSet.article.length-1; i++) {
            for (int j = 0; j < articleSet.article.length-i-1; j++) {
                if ( articleSet.article[j] != null && articleSet.article[j+1] != null) { // 只有不为null的元素才能参与排序
                    if ( articleSet.article[j].number < articleSet.article[j+1].number ) {
                        Article newTemp = articleSet.article[j];// 新定义一个临时变量，实现两个数据的交换位置
                        articleSet.article[j] = articleSet.article[j+1];
                        articleSet.article[j+1] = newTemp;


                        /*
                        冒泡排序算法
                        // 1 用空间换时间
                        int temp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = temp;
                        // 2 省空间
                        a[j] = a[j] + a[j+1];
                        a[j+1] = a[j] - a[j+1];
                        a[j] = a[j] - a[j+1];
                        // 3 完美
                        a[j] = a[j] ^ a[j+1];  // 1^0  相同为0  不同为1
                        a[j+1] = a[j] ^ a[j+1];
                        a[j] = a[j] ^ a[j+1];
                        */
                    }
                }
            }
        }
        // 打印结果
        System.out.println("名次" + "\t" + "商品" + "\t" + "售出数量");
        for (int i = 0; i < articleSet.article.length; i++) {
            if ( articleSet.article[i] != null ) {
                System.out.println( (i+1) + "\t" + articleSet.article[i].name + "\t" + articleSet.article[i].number);
            }
        }
    }

}
