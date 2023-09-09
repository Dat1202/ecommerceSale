import React, { useContext, useState } from "react";
import cookie from "react-cookies";
import { PayPalScriptProvider, PayPalButtons } from '@paypal/react-paypal-js';
import { MyCartContext } from "../../App";
import { authApis, endpoints } from "../../configs/Apis";


const PayPalPayment = (props) => {
    const [cart, setCart] = useState(cookie.load("cart") || null);
    const [, cartDispatch] = useContext(MyCartContext);
    const {sum} = props;
    const MyContext = React.createContext();

    const handlePaymentSuccess = async (details, data) => {
        // console.log(sum);

        // Xử lý kết quả thanh toán thành công
        try {
            let res = await authApis().post(endpoints['pay'], cart);
            if (res.status === 200) {
                cookie.remove("cart");
                setCart([]);

                cartDispatch({
                    "type": "update",
                    "payload": 0
                });
            }
            alert("Thanh toán thành công!");

            setTimeout(() => {
                window.location.reload();
            }, 1000);
        } catch (error) {
            // console.log(sum);
            // Xử lý lỗi nếu có
            console.error(error);
        }
        alert("Thanh toán thành công!");
    };

    const handlePaymentError = (error) => {
        // Xử lý lỗi thanh toán
        alert("Thanh toán bị lỗi!");
    };

    const handlePaymentCancel = (data) => {
        // Xử lý hủy thanh toán
        alert("Đã hủy");
    };

    return (
        <PayPalScriptProvider options={{ "client-id": "Aa-UiVsjDupBKkPHYLyzJ9mbPYkqlC5PXZVgI5olb1yPntRsi5ps9M4AGcSK_duLFNYLgqZG5b7PUMB1" }}>
            <PayPalButtons
                style={{
                    color:"silver", 
                    layout:"horizontal", 
                    height:48,
                    tagline: false,
                    shape: "pill"
                }}
                createOrder={(data, actions) => {
                    return actions.order.create({
                        purchase_units: [
                            {
                                amount: {
                                    value: sum, // Số tiền thanh toán
                                },
                            },
                        ],
                    });
                }}
                
                onApprove={(data, actions) => {
                    return actions.order.capture().then(function (details) {
                        handlePaymentSuccess(details, data);
                    });
                }}
                onError={handlePaymentError}
                onCancel={handlePaymentCancel}
            />
        </PayPalScriptProvider>
    );
};

export default PayPalPayment;