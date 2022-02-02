package net.miyukichan.utils;

import org.bukkit.util.Vector;

/**
 * Class holding utilities methods and fields of math.
 */
public final class MathUtils {

    public static Vector rotateAroundAxisY(Vector v, double angle) {
        double cos = Riven.cos(angle);
        double sin = Riven.sin(angle);
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }


}
