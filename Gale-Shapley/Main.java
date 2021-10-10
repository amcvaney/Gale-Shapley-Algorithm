//Abigayle McVaney
//CS 3364
//July 10th, 2021
//Gale Shapley Matching Algorithm 

public class Main
{
  //setting private variables for the public class Main
    private String[] men;
    private String[] women;
    private String[][] menprefer;
    private String[][] womenprefer;
    private String[] womenPartner;
    private int N, engagedCount;
    private boolean[] menEngaged;
 
    //creating the constructor
    public Main(String[] m, String[] w, String[][] mP, String[][] wP)
    {
        N = mP.length;
        engagedCount = 0;
        men = m;
        women = w;
        menprefer = mP;
        womenprefer = wP;
        menEngaged = new boolean[N];
        womenPartner = new String[N];
        calculateMatches();
    }

    /*function that calculates the matching for men and women. 

    First initializing all men and women to free.
    While a free man m who still has a woman w to propose to exists,
    w= m's highest ranked such woman that he hasnt proposed to yet.
    if w is free, (m,w) will become engaged. 
    Else, some pair (m',w) already exists; if w prefers m to m', (m,w) become engaged and m' then is free; else (m',w) remain engaged.
    */
    private void calculateMatches()
    {
      while (engagedCount < N)
      {
        int free;
        for (free = 0; free < N; free++)
          if (!menEngaged[free])
            break;
 
        for (int i = 0; i < N && !menEngaged[free]; i++)
          {
            int index = wIndex(menprefer[free][i]);
            if (womenPartner[index] == null)
            {
              womenPartner[index] = men[free];
              menEngaged[free] = true;
              engagedCount++;
            }
            else
            {
              String curPartner = womenPartner[index];
              
              if (morePref(curPartner, men[free], index))
              {
                womenPartner[index] = men[free];
                menEngaged[free] = true;
                menEngaged[mIndex(curPartner)] = false;
              }
            }
          }            
        }
      printCouples();
    }
  
    //function that checks if women prefers new partner over old given partner
    private boolean morePref(String curPartner, String newPartner, int index)
    {
      //loop for checking the women's prefences
      for (int i = 0; i < N; i++)
      { 
        if (womenprefer[index][i].equals(newPartner))
          return true;
        if (womenprefer[index][i].equals(curPartner))
          return false;
      }
      return false;
    }
    //gather's the men's index
    private int mIndex(String str)
    {
      //loop to scan men
      for (int i = 0; i < N; i++)
        if (men[i].equals(str))
          return i;
      return -1;
    }
    //gather's the men's index
    private int wIndex(String str)
    {
      //loop to scan women
      for (int i = 0; i < N; i++)
        if (women[i].equals(str))
          return i;
      return -1;
    }

    //function to print the couples matched
    public void printCouples()
    {
      System.out.println("The matched couples: ");
      for (int i = 0; i < N; i++)
      {
        System.out.println(womenPartner[i] +" "+ women[i]);
      }
    }

    //main function to print the output
    public static void main(String[] args) 
    {
      System.out.println("\nAbigayle McVaney");
      System.out.println("CS 3364");
      System.out.println("July 10th, 2021\n");
      System.out.println("Gale Shapley Matching Algorithm\n");

      System.out.println("name.(array position number)");

      //name.(array position number)
      //men list
      String[] m = {"Bob.1", "Larry.2", "John.3", "Mike.4", "Billy.5"};
      //women list 
      String[] w = {"Alice.1", "Kate.2", "Lily.3", "Tara.4", "Becky.5"};
 
      //preferences for the men 
      String[][] mP = {{"Becky.5", "Kate.2", "Lily.3", "Tara.4", "Alice.1"}, 
                      {"Kate.2", "Becky.5", "Alice.1", "Lily.3", "Tara.4"}, 
                      {"Tara.4", "Lily.3", "Kate.2", "Alice.1", "Becky.5"}, 
                      {"Alice.1", "Kate.2", "Lily.3", "Tara.4", "Becky.5"},
                      {"Becky.5", "Kate.2", "Lily.3", "Tara.4", "Alice.1"}};
        //preferences for the women                     
        String[][] wP = {{"Billy.5", "John.3", "Mike.4", "Bob.1", "Larry.2"}, 
                         {"Bob.1", "Larry.2", "John.3", "Billy.5", "Mike.4"}, 
                         {"Mike.4", "Billy.5", "John.3", "Larry.2", "Bob.1"},
                         {"Billy.5", "Larry.2", "Bob.1", "Mike.4", "John.3"}, 
                         {"Larry.2", "Bob.1", "Mike.4", "John.3", "Billy.5"}};
 
        Main gs = new Main(m, w, mP, wP);                 
    }
}