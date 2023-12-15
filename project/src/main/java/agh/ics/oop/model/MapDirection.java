package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    public String toString(){
        return switch(this){
            case NORTH -> "Polnoc";
            case SOUTH -> "Poludnie";
            case WEST -> "Zachod";
            case EAST -> "Wschod";
            case NORTH_EAST -> "Polnocny Wschod";
            case SOUTH_EAST -> "Poludniowy Wschod";
            case SOUTH_WEST -> "Poludniowy Zachod";
            case NORTH_WEST -> "Polnocny Zachod";
        };
    }

    public MapDirection previous() {
        int totalDirections = MapDirection.values().length;
        int previousOrdinal = (this.ordinal() + totalDirections - 1) % totalDirections;
        return MapDirection.values()[previousOrdinal];
    }

    public MapDirection next() {
        int totalDirections = MapDirection.values().length;
        int nextOrdinal = (this.ordinal() + totalDirections + 1) % totalDirections;
        return MapDirection.values()[nextOrdinal];
    }

    public Vector2d toUnitVector(){
        return switch(this){
            case NORTH -> new Vector2d(0,1);
            case WEST -> new Vector2d(-1,0);
            case EAST -> new Vector2d(1,0);
            case SOUTH -> new Vector2d(0,-1);
            case NORTH_EAST -> new Vector2d(1,1);
            case SOUTH_EAST -> new Vector2d(1,-1);
            case SOUTH_WEST -> new Vector2d(-1,-1);
            case NORTH_WEST -> new Vector2d(-1,1);
        };
    }

}
