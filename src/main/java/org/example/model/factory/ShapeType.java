package org.example.model.factory;

import org.example.model.MyShape;
import org.example.model.fill.FillBehavior;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public enum ShapeType {
    RECTANGULAR_SHAPE {
        @Override
        public MyShape createShape(FillBehavior fillBehavior, Color color) {
            return new MyShape(color, new Rectangle2D.Double(), fillBehavior);
        }
    },
    ELLIPSE_SHAPE {
        @Override
        public MyShape createShape(FillBehavior fillBehavior, Color color) {
            return new MyShape(color, new Ellipse2D.Double(), fillBehavior);
        }
    };


    public abstract MyShape createShape(FillBehavior fillBehavior, Color color);
}
