package org.dash.sdk.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseObject {
    protected abstract long getCPointer();

    public boolean equals(Object obj) {
        boolean equal = false;
        if (obj instanceof BaseObject)
            equal = (((BaseObject)obj).getCPointer() == this.getCPointer()) || baseObjectEquals((BaseObject) obj);
        return equal;
    }

    public int hashCode() {
        return (int)getCPointer();
    }

    protected boolean baseObjectEquals(BaseObject obj) {
        try {
            Method method = getClass().getMethod("objectEquals", getClass());
            Object result = method.invoke(this, obj);
            if (result instanceof Boolean) {
                return (Boolean) result;
            } else {
                System.out.printf("invoke returned type: %s, value: %s", result.getClass().getName(), result);
                return false;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            // swallow
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            // swallow
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}