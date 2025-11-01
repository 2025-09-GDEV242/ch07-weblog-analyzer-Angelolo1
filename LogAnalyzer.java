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
        reader = new LogfileReader(fileName);   //create the reader using the file name
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    /**
     * Exercise 7.13
     * Return the number of accesses rocorded in the log
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for (int count : hourCounts) {          //adds each value in hourCounts to the total
            total += count;
        }
        return total;
    }
    
    /**
     * Exercise 7.15
     * Return the busiest hour (hour with the highest accesses
     */
    
    

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
