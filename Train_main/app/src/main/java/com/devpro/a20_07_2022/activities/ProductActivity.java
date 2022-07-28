package com.devpro.a20_07_2022.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.devpro.a20_07_2022.R;
import com.devpro.a20_07_2022.listeners.BaseResponseListener;
import com.devpro.a20_07_2022.models.category.CategoryResponse;
import com.devpro.a20_07_2022.models.product.ProductDetailResponse;
import com.devpro.a20_07_2022.repository.ServiceImpl;
import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity {

    ServiceImpl service;
    ImageView image_product;
    TextView txt_title, txt_price, txtSoLuong;
    TextView btnAdd,btnSub, btn_DatHang, btn_exit;
    int soLuong = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        getViews();

        String id = getIntent().getStringExtra("id_product");

        service = new ServiceImpl(baseResponseListener);

        if (id != null) {
            service.getProductDetail(id);
        }

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void getViews() {
        image_product = findViewById(R.id.image_product);
        txt_title = findViewById(R.id.txt_title);
        txt_price = findViewById(R.id.txt_price);
        txtSoLuong = findViewById(R.id.txtSoLuong);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btn_DatHang = findViewById(R.id.btn_DatHang);
        btn_exit = findViewById(R.id.btn_exit);
    }

    BaseResponseListener baseResponseListener = new BaseResponseListener() {
        @Override
        public void didFetch(Object response) {
            if (response instanceof ProductDetailResponse) {
                ProductDetailResponse productDetailResponse = (ProductDetailResponse) response;
                Log.d("RESPONSEEE", ((ProductDetailResponse) response).product.get(0).product_name);

                Picasso.get().load(productDetailResponse.product.get(0).product_image).into(image_product);
                txt_title.setText(productDetailResponse.product.get(0).product_name);
                txt_price.setText(productDetailResponse.product.get(0).price + "");
                txtSoLuong.setText(soLuong+ "");

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        soLuong++;
                        txtSoLuong.setText(soLuong+ "");
                        txt_price.setText((productDetailResponse.product.get(0).price * soLuong) + "");
                    }
                });

                btnSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (soLuong > 1) {
                            soLuong--;
                            txtSoLuong.setText(soLuong+ "");
                            txt_price.setText((productDetailResponse.product.get(0).price * soLuong) + "");
                        }
                    }
                });
                btn_DatHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(ProductActivity.this)
                                .setTitle("Đặt Hàng")
                                .setMessage("Bạn đã đặt hàng thành công!")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        onBackPressed();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_info)
                                .show();
                    }
                });
            }
        }

        @Override
        public void didError(int code, String message) {
            Toast.makeText(ProductActivity.this, message, Toast.LENGTH_LONG).show();
        }
    };

    public void backClick(View view) {
        onBackPressed();
    }
}