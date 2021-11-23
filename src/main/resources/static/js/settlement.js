$(function () {
    //计算总价
    let array = $(".qprice");
    let totalCost = 0;
    for (let i = 0; i < array.length; i++) {
        let val = parseInt($(".qprice").eq(i).html().substring(1));
        totalCost += val;
    }
    $("#totalprice").html("￥"+totalCost);
    //cartsettlement2使用
    $("#settlement2_totalCost").val(totalCost);
});

//商品数量++
function addQuantity(obj) {
    let index = $(".car_btn_2").index(obj);
    let quantity = parseInt($(".car_ipt").eq(index).val());
    let stock = parseInt($(".productStock").eq(index).val());
    if (quantity == stock) {
        alert("库存不足")
        return false;
    }
    ++quantity;
    let price = parseFloat($(".productPrice").eq(index).val());
    let cost = quantity * price;
    let id = $(".id").eq(index).val();
    $.ajax({
        url: "/cart/updateCart/" + id + "/" + quantity + "/" + cost,
        type: "GET",
        success: function (data) {
            if (data == "success") {
                $(".qprice").eq(index).text('￥' + cost);
                $(".car_ipt").eq(index).val(quantity);
                let array = $(".qprice");
                let totalCost = 0;
                for (let i = 0; i < array.length; i++) {
                    let val = parseInt($(".qprice").eq(i).html().substring(1));
                    totalCost += val;
                }
                $("#totalprice").html("￥" + totalCost);
                $("#settlement2_totalCost").val(totalCost);
            }
        }
    });


    //
    // var cost = quantity*price;
    // $.ajax({
    //     url:"/product/updateCart/"+id+"/"+quantity+"/"+cost,
    //     type:"POST",
    //     dataType:"text",
    //     success:function (data) {
    //         if(data == "success"){
    //             //更新toSettlement的数据
    //             $(".qprice").eq(index).html("￥"+cost);
    //             inputObj.val(quantity);
    //             if(quantity < stock){
    //                 var totalCost = parseInt($("#totalprice").html().substring(1));
    //                 totalCost += price;
    //                 $("#totalprice").html("￥"+totalCost);
    //             }
    //             //更新searchBar的数据
    //             $(".quantity").eq(index).text(quantity);
    //             $(".cost").eq(index).text(cost);
    //
    //             var array = $(".cost");
    //             var totalCost = 0;
    //             for(var i = 0;i < array.length;i++){
    //                 var val = parseInt($(".cost").eq(i).html());
    //                 totalCost += val;
    //             }
    //             $("#totalCost").html("￥"+totalCost);
    //         }
    //     }
    // });
}

//商品数量--
function subQuantity(obj) {
    let index = $(".car_btn_1").index(obj);
    let quantity = parseInt($(".car_ipt").eq(index).val());
    if (quantity == 1) {
        alert("至少选择一件商品！");
        return false;
    }
    quantity--;
    let price = parseFloat($(".productPrice").eq(index).val());
    let cost = quantity * price;
    let id = parseInt($(".id").eq(index).val());
    $.ajax({
        url: "/cart/updateCart/" + id + "/" + quantity + "/" + cost,
        type: "GET",
        success: function (data) {
            if (data == "success") {
                $(".qprice").eq(index).text('￥' + cost);
                $(".car_ipt").eq(index).val(quantity);
                let array = $(".qprice");
                let totalCost = 0;
                for (let i = 0; i < array.length; i++) {
                    let val = parseInt($(".qprice").eq(i).html().substring(1));
                    totalCost += val;
                }
                $("#totalprice").html("￥" + totalCost);
                $("#settlement2_totalCost").val(+totalCost);
            }
        }
    });
    // var index = $(".car_btn_1").index(obj);
    // var price = parseInt($(".productPrice").eq(index).val());
    // var inputObj = $(".car_ipt").eq(index);
    // var quantity = inputObj.val();
    // var id = $(".id").eq(index).val();
    // --quantity;
    // if(quantity == 0){
    //     quantity = 1;
    // }
    // var cost = quantity*price;
    // $.ajax({
    //     url:"/product/updateCart/"+id+"/"+quantity+"/"+cost,
    //     type:"POST",
    //     dataType:"text",
    //     success:function(data){
    //         if(data == "success"){
    //             $(".qprice").eq(index).html("￥"+cost);
    //             inputObj.val(quantity);
    //             if(quantity!=1){
    //                 var totalCost = parseInt($("#totalprice").html().substring(1));
    //                 totalCost -= price;
    //                 $("#totalprice").html("￥"+totalCost);
    //             }
    //             $(".quantity").eq(index).text(quantity);
    //             $(".cost").eq(index).text(cost);
    //
    //             var array = $(".cost");
    //             var totalCost = 0;
    //             for(var i = 0;i < array.length;i++){
    //                 var val = parseInt($(".cost").eq(i).html());
    //                 totalCost += val;
    //             }
    //             $("#totalCost").html("￥"+totalCost);
    //         }
    //     }
    // });
}

//移出购物车
function removeCart(obj) {
    let index = $(".delete").index(obj);
    let id = $(".id").eq(index).val();
    if (confirm("是否确定要删除？")) {
        window.location.href = "/cart/removeCart/" + id;
    }
}

function confirmOrder(){
    let totalCost = $("totalprice").text();
    if(totalCost == "￥0"){
        alert("购物车为空，不能结算！");
        return false;
    }
    window.location.href="/cart/confirmOrder";
}