package com.amirchev.contents;

public class FieldContentFactory {
    public enum FieldContentEnum {
        APPLE {
            public FieldContent getInstance() {
                return new Apple();
            }
        },
        BRICK {
            public FieldContent getInstance() {
                return new Brick();
            }
        },
        SNAKE_SEGMENT {
            public FieldContent getInstance() {
                return new SnakeSegment();
            }
        };

        public abstract FieldContent getInstance();
    }
    public static FieldContent make(final FieldContentEnum content) {
        return content.getInstance();
    }
}
