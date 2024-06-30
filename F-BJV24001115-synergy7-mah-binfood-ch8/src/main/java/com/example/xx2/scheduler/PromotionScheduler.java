package com.example.xx2.scheduler;

import com.example.xx2.model.Merchant;
import com.example.xx2.model.Product;
import com.example.xx2.model.dto.NotificationRequestDto;
import com.example.xx2.service.FCMService;
import com.example.xx2.service.ProductService;
import com.example.xx2.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class PromotionScheduler {
    @Autowired
    ProductService productService;

    @Autowired
    PromotionService promotionService;

    @Autowired
    private FCMService fcmService;

    @Scheduled(cron = "0 0 12 * * *") //setiap jam 12 siang 0 0 12 * * *
    public void cronJob(){
        List<Product> productPromoList = productService.getAll();
        promotionService.send(productPromoList);

        Map<Merchant, List<Product>> productsByMerchant = productPromoList.stream()
                .collect(Collectors.groupingBy(Product::getMerchant));

        productsByMerchant.forEach((merchant, products) -> {
            String productList = products.stream()
                    .map(Product::getProduct_name)
                    .collect(Collectors.joining(", "));

            NotificationRequestDto request = new NotificationRequestDto();
            request.setTitle("Promo Produk dari " + merchant.getMerchant_name());
            request.setBody("Promosi Produk: " + productList + "sebesar 20% tiap produknya");
//            request.setTopic("promotion");
            request.setToken("e7XBUS-JL0XSvQkE2kEKLT:APA91bG1CAf2NI-D00UZ1Dst7mAafY8BwTH4cDNRE3SbM26KAxm3jx6ukp3U6OWEfZpKEZ7heOiqHkHbVyDSHtRY-8OAozSYjbn1C2coj9n_EmhO1I4xEh5bzVoco1oUPpu9nVzT226K"); // Asumsikan Merchant memiliki userToken untuk FCM

            try {
                fcmService.sendMessageToToken(request);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
