package model;

public class WalkingTime {
    private Entity from;
    private Entity to;
    private int duration;

    public WalkingTime(){}

    public WalkingTime(Entity from, Entity to, int duration) {
        this.from = from;
        this.to = to;
        this.duration = duration;
    }

    public Entity getFrom() {
        return from;
    }

    public void setFrom(Entity from) {
        this.from = from;
    }

    public Entity getTo() {
        return to;
    }

    public void setTo(Entity to) {
        this.to = to;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "WalkingTime{" +
                "from=" + from +
                ", to=" + to +
                ", duration=" + duration +
                '}';
    }
}
