/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    //7.19 Array to hold daily access counts
    private int[] dayCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        //7.19 Create the array object to hold the daily access counts
        dayCounts = new int [31];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }
    
    /**
     * Exercise 7.12
     * Create an object to analyze hourly web accesses from the file
     * @param fileName the name of the log file to analyze
     */
    public LogAnalyzer(String fileName)
    {
        hourCounts = new int[24];               //create the array object to hold hourly access counts
        dayCounts = new int[31];                //create the array object to hold daily access counts
        reader = new LogfileReader(fileName);   //create the reader using the file name
    }

    /**
     * 7.19
     * Updated to analyze both hourly and daily access data from the log file.
     */
    public void analyzeData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            int day = entry.getDay();
            
            if (hour >= 0 && hour < 24) {
                hourCounts[hour]++;
            }
            if (day >= 1 && day <= 31) {
                dayCounts[day - 1]++;
            }
        }
    }
    
    /**
     * Exercise 7.13
     * Return the number of accesses rocorded in the log
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for (int count : hourCounts) {     //adds each value in hourCounts to the total
            total += count;
        }
        return total;
    }
    
    /**
     * Exercise 7.15
     * Return the busiest hour (hour with the highest number of accesses
     */
    public int busiestHour()
    {
        int busiest = 0;
        for (int hour = 1; hour < hourCounts.length; hour++) {
            if (hourCounts[hour] > hourCounts[busiest]) {
                busiest = hour;
            }
        }
        return busiest;
    }
    
    /**
     * Exercise 7.16
     * Return the quietest hour (hour with the lowest non-zero number of accesses)
     */
    public int quietestHour()
    {
        int quietest = -1;
        for (int i = 0; i < hourCounts.length; i++) {
            if (hourCounts[i] > 0) {
                quietest = i;
                break;
            }
        }
        
        if (quietest == -1) {      //if all counts are zero, return -1
            return -1;
        }
        
        for (int hour = 0; hour < hourCounts.length; hour++) {
            if (hourCounts[hour] > 0 && hourCounts[hour] < hourCounts[quietest])
            quietest = hour;

        }
        return quietest;
    }
    
    /**
     * Exercise 7.18
     * Return the first hour of the busiest two-hour period
     */
    public int busiestTwoHour()
    {
        int busiestStart = 0;
        int maxTotal = hourCounts[0] + hourCounts[1];
        
        for (int hour = 1; hour < 24; hour++) {
            int next = (hour + 1) % 24;            //wraps around from 23 to 0
            int total = hourCounts[hour] + hourCounts[next];
            if (total > maxTotal) {
                maxTotal = total;
                busiestStart = hour;
            }
        }
        return busiestStart;
    }
    
    /**
     * Exercise 7.19
     * Return the busiest day (day with the most accesses)
     */
    public int busiestDay()
    {
        int busiest = 0;
        for (int day = 1; day < dayCounts.length; day++) {
            if (dayCounts[day] > dayCounts[busiest]) {
                busiest = day;
            }
        }
        return busiest + 1;     //+1 since array index 0 = Day 1
    }
    
    /**
     * Exercise 7.19
     * Return the quietest day (day with the lowest non-zero number of accesses)
     */
    public int quietestDay()
    {
        int quietest = -1;
        for (int i = 0; i < dayCounts.length; i++) {
            if (dayCounts[i] > 0) {
                quietest = i;
                break;
            }
        }
        
        if (quietest == -1) {
            return -1;  //no data
        }
        
        for (int day = 0; day < dayCounts.length; day++) {
            if (dayCounts[day] > 0 && dayCounts[day] < dayCounts[quietest]) {
                quietest = day;
            }
        }
        return quietest + 1;  //+1 since array index 0 = Day 1
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the daily counts
     */
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
