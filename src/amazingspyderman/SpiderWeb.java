/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazingspyderman;


/**
 *
 * @author wyatt_ofn2t9u
 */
public class SpiderWeb {
    /**
     * This is our test. It creates a spider (which creates spider legs) and crawls the web.
     * 
     * @param args
     *            - not used
     */
    public static void main(String[] args)
    {
        Spyder spider = new Spyder();
        spider.search("https://wyattkarnes.me/", "poison");
    }
}
