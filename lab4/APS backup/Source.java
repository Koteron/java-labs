public class Source
{
    private static final double MAX_GENERATION_TIME = 10.0;
    private static final double MIN_GENERATION_TIME = 2.0;
    private int sourceNumber = -1;
    private int rejectedRequestAmount = 0;
    private int requestAmount = 0;
    private double nextGenerationTime;
    private int lastRequestNumber = -1;
    private final DispatchInput dispatchInput;
    private double lastGenerationTime = 0.0;

    public Source(int sourceNum, DispatchInput dispInput, double currentTime)
    {
        nextGenerationTime = calculateNextGenerationTime(currentTime);
        sourceNumber = sourceNum;
        dispatchInput = dispInput;
    }
    public int getSourceNumber()
    {
        return sourceNumber;
    }
    public int getRequestAmount()
    {
        return requestAmount;
    }
    public int getAcceptedRequestAmount()
    {
        return requestAmount - rejectedRequestAmount;
    }
    public int getRejectedRequestAmount()
    {
        return rejectedRequestAmount;
    }
    public void increaseRejected() { ++rejectedRequestAmount; }
    public double calculateNextGenerationTime(double currentTime)
    {
        nextGenerationTime = currentTime + Math.random()*(MAX_GENERATION_TIME-MIN_GENERATION_TIME)+MIN_GENERATION_TIME;
        return nextGenerationTime;
    }
    public double getNextGenerationTime() { return nextGenerationTime; }
    public double getLastGenerationTime() { return lastGenerationTime; }
    public boolean sendRequest(double currentTime)
    {
        calculateNextGenerationTime(currentTime);
        Request req = new Request(sourceNumber);
        lastRequestNumber = req.getRequestNumber();
        if (dispatchInput.queueRequest(req))
        {
            lastGenerationTime = currentTime;
            ++requestAmount;
            return true;
        }
        return false;
    }

    public int getLastRequestNumber() {
        return lastRequestNumber;
    }
}
