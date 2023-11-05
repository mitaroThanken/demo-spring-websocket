package demo.websocket.server.example4.domain;

import java.util.StringJoiner;

public class Performance {

    private long time;

    private long committedVirtualMemorySize;

    private long totalSwapSpaceSize;
    private long freeSwapSpaceSize;

    private long totalMemorySize;
    private long freeMemorySize;

    private double cpuLoad;
    private double processCpuLoad;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getCommittedVirtualMemorySize() {
        return committedVirtualMemorySize;
    }

    public void setCommittedVirtualMemorySize(long committedVirtualMemorySize) {
        this.committedVirtualMemorySize = committedVirtualMemorySize;
    }

    public long getTotalSwapSpaceSize() {
        return totalSwapSpaceSize;
    }

    public void setTotalSwapSpaceSize(long totalSwapSpaceSize) {
        this.totalSwapSpaceSize = totalSwapSpaceSize;
    }

    public long getFreeSwapSpaceSize() {
        return freeSwapSpaceSize;
    }

    public void setFreeSwapSpaceSize(long freeSwapSpaceSize) {
        this.freeSwapSpaceSize = freeSwapSpaceSize;
    }

    public long getTotalMemorySize() {
        return totalMemorySize;
    }

    public void setTotalMemorySize(long totalMemorySize) {
        this.totalMemorySize = totalMemorySize;
    }

    public long getFreeMemorySize() {
        return freeMemorySize;
    }

    public void setFreeMemorySize(long freeMemorySize) {
        this.freeMemorySize = freeMemorySize;
    }

    public double getCpuLoad() {
        return cpuLoad;
    }

    public void setCpuLoad(double cpuLoad) {
        this.cpuLoad = cpuLoad;
    }

    public double getProcessCpuLoad() {
        return processCpuLoad;
    }

    public void setProcessCpuLoad(double processCpuLoad) {
        this.processCpuLoad = processCpuLoad;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Performance.class.getSimpleName() + "[", "]")
                .add("time=" + time)
                .add("committedVirtualMemorySize=" + committedVirtualMemorySize)
                .add("totalSwapSpaceSize=" + totalSwapSpaceSize)
                .add("freeSwapSpaceSize=" + freeSwapSpaceSize)
                .add("totalMemorySize=" + totalMemorySize)
                .add("freeMemorySize=" + freeMemorySize)
                .add("cpuLoad=" + cpuLoad)
                .add("processCpuLoad=" + processCpuLoad)
                .toString();
    }
}
