package org.firstinspires.ftc.teamcode.entites;

public final class Pointer<T> {
    private T obj;
    public Pointer(T obj){
        setObj(obj);
    }

    public void setObj(T obj){
        this.obj = obj;
    }

    public static Pointer<Boolean> toBooleanPointer(Boolean obj){
        return new Pointer<>(obj);
    }

    public static Pointer<Integer> toIntPointer(Integer obj){
        return new Pointer<>(obj);
    }

    public T getObj(){
        return obj;
    }
}
