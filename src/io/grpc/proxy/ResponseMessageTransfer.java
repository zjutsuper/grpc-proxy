package io.grpc.proxy;

import java.io.Serializable;

/**
 * Created by hz-zjutzheng on 17/4/6.
 */
public class ResponseMessageTransfer implements Serializable {
    private static final long serialVersionUID = -6236564389036692391L;

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
