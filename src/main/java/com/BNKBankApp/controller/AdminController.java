package com.BNKBankApp.controller;
import com.BNKBankApp.data.model.Inventory;
import com.BNKBankApp.data.model.Order;
import com.BNKBankApp.data.model.Product;
import com.BNKBankApp.dto.request.AddProductRequest;
import com.BNKBankApp.dto.request.AdminRegisterRequest;
import com.BNKBankApp.dto.request.CreateCategoryRequest;
import com.BNKBankApp.dto.request.FindProductRequest;
import com.BNKBankApp.dto.resonse.AddedProductResponse;
import com.BNKBankApp.dto.resonse.AdminRegisterResponse;
import com.BNKBankApp.dto.resonse.CreatedCategoryResponse;
import com.BNKBankApp.dto.resonse.ProcessOrderResponse;
import com.BNKBankApp.service.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;


    @PostMapping("/register")
    public ResponseEntity<AdminRegisterResponse> register(@RequestBody @Valid AdminRegisterRequest adminRegisterRequest) {
        return ResponseEntity.ok(adminService.registerAdmin(adminRegisterRequest));
    }

    @PostMapping("/createCategory")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreatedCategoryResponse> createCategory(@RequestBody @Valid CreateCategoryRequest createCategoryRequest){
        return ResponseEntity.ok(adminService.createCategory(createCategoryRequest));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<AddedProductResponse> addProduct(@RequestBody @Valid AddProductRequest addProductRequest){
        return ResponseEntity.ok(adminService.addProduct(addProductRequest));
    }

    @GetMapping("/getAllInventory")
    public ResponseEntity<List<Inventory>> getAllInventory(){
        return ResponseEntity.ok(adminService.getAllInventory());
    }
    @DeleteMapping("/deleteProduct")
    public ResponseEntity <String> removeProduct(@RequestBody @Valid FindProductRequest findProductRequest){
        adminService.removeProduct(findProductRequest);
        return ResponseEntity.ok("Product Removed");
    }
    @GetMapping("/findProductByName")
    public ResponseEntity <Product> findProductByName(@RequestBody @Valid FindProductRequest findProductRequest){
        return ResponseEntity.ok(adminService.findProduct(findProductRequest));
    }

    @GetMapping("/findProductById")
    public ResponseEntity <Inventory> findProductById(@RequestBody @Valid String Id){
        return ResponseEntity.ok(adminService.findByProductId(Id));
    }
    @GetMapping("/checkOrderList")
    public ResponseEntity <List<Order>> checkOrderList(){
        return ResponseEntity.ok(adminService.checkListOfOrders());
    }

    @GetMapping("/processOrderResponse")
    public ResponseEntity <ProcessOrderResponse> processOrder(@RequestBody @Valid String orderId){
        return ResponseEntity.ok(adminService.processOrder(orderId));
    }
}
