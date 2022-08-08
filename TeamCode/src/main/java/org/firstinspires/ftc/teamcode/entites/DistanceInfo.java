package org.firstinspires.ftc.teamcode.entites;

import java.util.function.Consumer;

public class DistanceInfo {
    public final int index;
    public final double limit;
    public final Consumer<Integer> onLimitExceeded;
    public DistanceInfo(int index, double limit, Consumer<Integer> onLimitExceeded){
        this.index = index;
        this.limit = limit;
        this.onLimitExceeded = onLimitExceeded;
    }
}
