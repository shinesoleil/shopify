//package com.tw.store.controller;
////
//import com.tw.store.ProductClient;
//import com.tw.store.domain.Product;
//import com.tw.store.domain.ProductPrice;
//import com.tw.store.domain.ProductPriceRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.client.RestTemplate;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.UriInfo;
//import java.net.URI;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//@Path("/products/{pid}")
//public class ProductPricesController {
//
//    @Autowired
//    ProductPriceRepository productPriceRepository;
//
//    @Autowired
//    private DiscoveryClient discoveryClient;
////
////    private RestTemplate restTemplate = new RestTemplate();
//
//    @Autowired
//    ProductClient productClient;
//
//    @Context
//    UriInfo uriInfo;
//
//    @GET
//    @Path("/prices")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<ProductPrice> getAllPricesOfProduct(@PathParam("pid") String productId){
//        return productPriceRepository.findAllByProductId(productId);
//    }
//
//    @POST
//    @Path("/prices")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createProductPrice(HashMap<String, Object> info, @PathParam("pid") String productId){
//        Product current = productClient.getProduct(productId);
//
//        if (!info.containsKey("unit_price")) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
//
//        ProductPrice productPrice = new ProductPrice();
//        productPrice.setProductId(productId);
//        productPrice.setProductName(current.getName());
//        productPrice.setUnitPrice(Double.parseDouble(info.get("unit_price").toString()));
//
//        ProductPrice createdProductPrice = productPriceRepository.save(productPrice);
//
//        URI createdLocation = URI.create(String.format("%s/%s", uriInfo.getAbsolutePath(), createdProductPrice.getId()));
//
//        return Response.status(Response.Status.CREATED).location(createdLocation).build();
//    }
//
//
//    @GET
//    @Path("/current-price")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ProductPrice getCurrentPrice(@PathParam("pid") String productId) {
//
//        return productPriceRepository.findFirstByProductIdOrderByCreatedAtDesc(productId)
//                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
//    }
//
//    @Path("/prices/{ppid}")
//    public ProductPriceController getPrice(@PathParam("ppid") String productPriceId) {
//
//        return productPriceRepository.findById(productPriceId)
//                .map(ProductPriceController::new)
//                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
//    }
//}