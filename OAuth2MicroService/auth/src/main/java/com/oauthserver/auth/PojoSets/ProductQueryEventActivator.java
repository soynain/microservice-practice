package com.oauthserver.auth.PojoSets;

import java.util.concurrent.CountDownLatch;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQueryEventActivator {
    private CountDownLatch latch=new CountDownLatch(0);
    private String productQueryJsonStringUnparsed;
    

    
}
