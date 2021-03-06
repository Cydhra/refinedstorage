package com.raoulvdberge.refinedstorage.render.constants;

import com.raoulvdberge.refinedstorage.render.collision.CollisionGroup;
import com.raoulvdberge.refinedstorage.util.CollisionUtils;

public final class ConstantsExternalStorage {
    public static final CollisionGroup HEAD_NORTH = new CollisionGroup().addItem(CollisionUtils.getBounds(3, 3, 0, 13, 13, 2)).setCanAccessGui(true);
    public static final CollisionGroup HEAD_EAST = new CollisionGroup().addItem(CollisionUtils.getBounds(14, 3, 3, 16, 13, 13)).setCanAccessGui(true);
    public static final CollisionGroup HEAD_SOUTH = new CollisionGroup().addItem(CollisionUtils.getBounds(3, 3, 14, 13, 13, 16)).setCanAccessGui(true);
    public static final CollisionGroup HEAD_WEST = new CollisionGroup().addItem(CollisionUtils.getBounds(0, 3, 3, 2, 13, 13)).setCanAccessGui(true);
    public static final CollisionGroup HEAD_UP = new CollisionGroup().addItem(CollisionUtils.getBounds(3, 14, 3, 13, 16, 13)).setCanAccessGui(true);
    public static final CollisionGroup HEAD_DOWN = new CollisionGroup().addItem(CollisionUtils.getBounds(3, 0, 3, 13, 2, 13)).setCanAccessGui(true);
}
