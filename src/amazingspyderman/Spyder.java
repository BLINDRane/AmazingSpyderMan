
package amazingspyderman;

/**
 *
 * @author wyatt_ofn2t9u
 */
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Scanner;
public class Spyder
{
  private int pagesToSearch = 10;
  private Set<String> pagesVisited = new HashSet<String>();
  private List<String> pagesToVisit = new LinkedList<String>();
  private Scanner in = new Scanner(System.in);
  private boolean success;
  /**
   * Our main launching point for the Spider's functionality. Internally it creates spider legs
   * that make an HTTP request and parse the response (the web page).
   * 
   * @param url
   *            - The starting point of the spider
   * @param searchWord
   *            - The word or string that you are searching for
   */
  public void search(String url, String searchWord)
  {
      while(this.pagesVisited.size() < pagesToSearch)
      {
          String currentUrl;
          Leg leg = new Leg();
          if(this.pagesToVisit.isEmpty())
          {
              currentUrl = url;
              this.pagesVisited.add(url);
          }
          else
          {
              currentUrl = this.nextUrl();
          }
          leg.crawl(currentUrl); // Lots of stuff happening here. Look at the crawl method in
                                 // SpiderLeg
          success = leg.searchForWord(searchWord);
          if(success)
          {
              System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
              break;
          } else {
              System.out.println(String.format("**Failure** Word '%s' not found at %s", searchWord, currentUrl));
          }
          this.pagesToVisit.addAll(leg.getLinks());
      }
      if(!success){
          System.out.println("Our little friend could not find the word you were looking for.");
          System.out.println("Continue searching? yes or no");
              String input = in.nextLine();
              switch(input){
                  case "yes":
                      pagesToSearch += pagesToSearch;
                      search(url, searchWord);
                      break;
                  case "no":                      
                      break;
             }
      }
      System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)");
  }


  /**
   * Returns the next URL to visit (in the order that they were found). We also do a check to make
   * sure this method doesn't return a URL that has already been visited.
   * 
   * @return
   */
  private String nextUrl()
  {
      String nextUrl;
      do
      {
          nextUrl = this.pagesToVisit.remove(0);
      } while(this.pagesVisited.contains(nextUrl));
      this.pagesVisited.add(nextUrl);
      return nextUrl;
  }
}