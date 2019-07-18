package Thread02;

/**
 * 利用CommonsIO和继承Thread类同时下载资源
 */
public class WebDownload extends Thread{

    private String url;
    private String name;

    public WebDownload(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloadUtils wdu = new WebDownloadUtils();
        wdu.download(this.url, this.name);
        System.out.println(this.name + "-->Downloading...");
    }

    public static void main(String[] args) {
        WebDownload wd1 = new WebDownload("http://www.personal.psu.edu/bvt5083/picFormats/earth.jpg",
                "src/resources/butterfly.jpg");
        WebDownload wd2 = new WebDownload("http://www.personal.psu.edu/bvt5083/picFormats/earth.jpg",
                "src/resources/cat.jpg");
        WebDownload wd3 = new WebDownload("http://www.personal.psu.edu/bvt5083/picFormats/earth.jpg",
                "src/resources/earth.jpg");


        wd1.start();
        wd2.start();
        wd3.start();
    }
}
