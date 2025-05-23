/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Oscar Pinto
 * @version    4-7-2025
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

     /**
    * Create an object to analyze hourly web accesses.
    * @param filename The file to be analyzed.
    */
    public LogAnalyzer(String filename)
    {
    // Create the array object to hold the hourly
    // access counts.
    hourCounts = new int[24];
    // Create the reader to obtain the data.
    reader = new LogfileReader(filename);
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
    * Return the busiest hour of day
    */
    public int busiestHour()
    {   
    int busiestHour = 0;
    for(int hour = 1; hour < hourCounts.length; hour++) {
    if(hourCounts[hour] > hourCounts[busiestHour]) {
    busiestHour = hour;
    }
    }
    return busiestHour;
    }

    /**
    * Return the quietest hour of day
    */
        public int quietestHour()
    {
    int quietestHour = 0;
    for(int hour = 1; hour < hourCounts.length; hour++) {
    if(hourCounts[hour] < hourCounts[quietestHour]) {
    quietestHour = hour;
    }
    }
    return quietestHour;
    }

    /**
    * Return the two-hour period which is busiest.
    */
    public int busiestTwoHourPeriod()
    {
    int busiestPeriod = 0;
    int busiestPeriodCount = 0;
    for(int hour = 0; hour < hourCounts.length - 1; hour++) {
    int periodCount = hourCounts[hour] +
    hourCounts[hour+1];
    if(periodCount > busiestPeriodCount) {
    busiestPeriod = hour;
    busiestPeriodCount = periodCount;
    }
    }
    return busiestPeriod;
    }
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
