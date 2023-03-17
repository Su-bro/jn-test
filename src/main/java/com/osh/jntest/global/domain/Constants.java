package com.osh.jntest.global.domain;

import java.util.Arrays;
import lombok.Getter;

public class Constants {

    //Result constants
    // Common 00xx
    public final static int RC_SUCCESS = 0000;
    public final static int RC_FAILURE = -0001;
    // Use
    public final static int RC_POINT_NOT_ENOUGH = -1010;
    public final static int RC_ADMIN_DEDUCT_FAILURE = -1020;
    public final static int RC_ADMIN_DEDUCT_INVALID_USER = -1021;
    // Shop 20xx
    public final static int RC_USER_NOT_EXIST = -2010;
    public final static int RC_DEDUCT_ALREADY_DONE = -2011;
    public final static int RC_DEDUCT_FAILURE = -2012;
    public final static int RC_DEDUCT_INVALID_POINT = -2013;
    public final static int RC_REFUND_POINT_NOT_FOUND = -2020;
    public final static int RC_REFUND_POINT_ZERO = -2021;
    // Offerwall 30xx
    public final static int RC_OFFERWALL_ALREADY_DONE = -3010;

    public enum Keys {

        CODE("code"),
        MESSAGE("message"),
        DETAIL("detail");

        @Getter
        private String key;

        Keys(String key) {
            this.key = key;
        }
    }

    public enum Code {
        SUCCESS(0, "SUCCESS"),
        FAILURE(-1, "FAILURE");

        @Getter
        private int code;

        @Getter
        private String message;

        Code(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    public enum UserJoin {

        EMAIL(0, "이메일"),
        NAVER(1, "네이버"),
        KAKAO(2, "카카오톡"),
        FACEBOOT(3, "페이스북"),
        NAVER_CAFE(4, "네이버 카페"),
        PAYCO(5, "페이코");

        @Getter
        private int code;

        @Getter
        private String name;

        UserJoin(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getUserJoinCodeToName(int code) {
            return Arrays.stream(UserJoin.values())
                .filter(join -> join.getCode() == code)
                .findAny()
                .orElse(EMAIL)
                .getName();
        }

        public static UserJoin getUserJoinCodeToType(int code) {
            return Arrays.stream(UserJoin.values())
                .filter(join -> join.getCode() == code)
                .findAny()
                .orElse(EMAIL);
        }
    }

    public static enum POINT_MASTER_USE_YN {
        UNUSED(0),
        USED(1);

        private final int yn;

        POINT_MASTER_USE_YN(int i) {
            this.yn = i;
        }

        public int getValue() {
            return this.yn;
        }
    }

    public static enum POINT_MASTER_USE_TYPE {
        ADD_POINT(0), // 적립
        USE_POINT(1), // 사용
        EXPIRY_POINT(2), // 말소
        SPARED_POINT(3), // 나머지
        USE_CANCEL_POINT(4),    //사용취소
        ADD_CANCEL_POINT(5)    //적립취소
        ;

        private final int type;

        POINT_MASTER_USE_TYPE(int i) {
            this.type = i;
        }

        public int getValue() {
            return this.type;
        }
    }

    public static enum ORDER_TYPE {
        DEFAULT(0),
        //즉시송금
        PAYCO(2),
        KAKAOPAY(3),
        TOSS(4),

        //편의점택배
        JN_DELIVERY(6),

        //안전결제
        UNICRO_CARD(11),
        UNICRO_BANK(12),
        UNICRO_DEPOSITLESS(13),
        KB_APP_CARD(14),
        SETTLE(50),

        // 판매완료 >  직거래
        SHIPPING_JN(5),
        DIRECT_JN(7),
        OTHER_JN(8),
        ETC_JN(9),

        //파트너스센터 (인증)
        PARTNER_CENTER_CONSIGNMENT(10),
        SHIPPING_PARTNER_CENTER(15),
        DIRECT_PARTNER_CENTER(17),
        OTHER_PARTNER_CENTER(18),
        ETC_PARTNER_CENTER(19),

        //오토
        DEALER_AUTO(25),
        DIRECT_AUTO(27),
        OTHER_AUTO(28),
        ETC_AUT(29),

        // 판매완료 > 종료
        CLOSE_JN(30);

        private final int type;

        ORDER_TYPE(int i) {
            this.type = i;
        }

        public int getValue() {
            return this.type;
        }

    }

    public static enum POINT_TYPE {
        POINT(0),
        CASH(1);

        private final int pointType;

        POINT_TYPE(int i) {
            this.pointType = i;
        }

        public int getValue() {
            return this.pointType;
        }
    }

    public enum OrderStatus {

        //공통
        DEFAULT("99", "기본값", "", ""),
        ORDER_COMPLETED("00", "결제시작", "13", "입금대기"),

        //유니크로
        PAYMENT_CANCEL("88", "PG 결제진행 중 취소", "", ""),
        PAYMENT_COMPLETED("01", "결제완료", "31", "결제완료"),
        DELIVERY_ENTER("02", "배송현황 기입 / 중고나라 편의점택배 배송현황 기입", "32F", "배송중"),
        PURCHASE_CONFIRM("03", "구매확정", "33", "거래완료"),
        RETURN_REQUEST("05", "반품신청", "51", "반품요청"),
        RETURN_ACCEPT("06", "반품수락", "52", "반품동의"),
        RETURN_DELIVERY_ENTER("07", "반품배송현황 기입 / 중고나라 편의점택배 반품배송현황 기입", "53", "반품배송중"),
        DEPOSITLESS_CANCEL("08", "무통장 거래취소(입금기간 만료 / 타 회원의 결제완료)", "14", "입금취소"),
        DEPOSITLESS_WAITING("09", "무통장 입금예정", "", ""),
        RETURN_COMPLETED("10", "반품완료", "54", "반품완료"),
        SALE_ACCEPT("11", "판매수락", "61", "주문확인"),
        DELIVERY_RESERVATION("21", "편택예약중", "", ""),
        DELIVERY_COMPLETED("23", "배송완료", "32", "배송중"),
        ORDER_CANCEL_REQUEST("80", "거래취소 요청", "", ""),
        SELLER_CANCEL("81", "판매자 취소", "36", "판매취소완료"),
        BUYER_CANCEL("82", "구매자 취소", "35", "결제취소완료"),
        ADMIN_CANCEL("83", "관리자 취소", "37", "운영자거래취소"),
        BUYER_CANCEL_REQUEST("84", "구매자 취소요청", "34", "결제취소요청"),

        //즉시송금(페이코, 카카오페이, 토스)
        TRADE_COMPLETED("60", "즉시송금-거래완료", "", ""),
        TRADE_WAITING("61", "즉시송금-송금대기", "", ""),
        TRADE_FAIL("62", "즉시송금-송금 대기 후 실패", "", ""),

        //직거래
        DIRECT_TRADE_COMPLETED("50", "직거래 - 구매자있음", "", ""),
        DIRECT_TRADE_NO_BUYER("51", "직거래 - 구매자없음", "", ""),

        //편의점택배 거래
        JN_DELIVERY_CANCEL("59", "편의점택배 예약 취소", "", ""),

        //인증거래(평화시장) 거래타입: 10, 거래상태: 30
        PARTNER_CENTER_COMPLETED("30", "인증상품 거래완료", "", ""),
        PARTNER_CENTER_CANCEL("35", "인증상품 주문취소", "", "");

        @Getter
        private String jnStatus;

        @Getter
        private String jnDescription;

        @Getter
        private String unicroStatus;

        @Getter
        private String unicroDescription;

        OrderStatus(String jnStatus, String jnDescription, String unicroStatus, String unicroDescription) {
            this.jnStatus = jnStatus;
            this.jnDescription = jnDescription;
            this.unicroStatus = unicroStatus;
            this.unicroDescription = unicroDescription;
        }

        /**
         * 중고나라 상태코드로 enum name get
         *
         * @param jnStatus jnStatus
         * @return enum.name()
         */
        public static String getEnumNameByJnStatus(String jnStatus) {
            for (OrderStatus orderStatus : OrderStatus.values()) {
                if (orderStatus.jnStatus.equals(jnStatus)) {
                    return orderStatus.name();
                }
            }
            return null;
        }

        public static boolean isCompletedOrder(String orderStatus) {
            return orderStatus.equals(OrderStatus.PURCHASE_CONFIRM.getJnStatus())
                || orderStatus.equals(OrderStatus.TRADE_COMPLETED.getJnStatus())
                || orderStatus.equals(OrderStatus.DIRECT_TRADE_COMPLETED.getJnStatus())
                || orderStatus.equals(OrderStatus.DIRECT_TRADE_NO_BUYER.getJnStatus())
                || orderStatus.equals(OrderStatus.PARTNER_CENTER_COMPLETED.getJnStatus())

                || orderStatus.equals(OrderStatus.RETURN_COMPLETED.getJnStatus())
                || orderStatus.equals(OrderStatus.PAYMENT_CANCEL.getJnStatus())
                || orderStatus.equals(OrderStatus.DEPOSITLESS_CANCEL.getJnStatus())
                || orderStatus.equals(OrderStatus.SELLER_CANCEL.getJnStatus())
                || orderStatus.equals(OrderStatus.BUYER_CANCEL.getJnStatus())
                || orderStatus.equals(OrderStatus.ADMIN_CANCEL.getJnStatus())
                || orderStatus.equals(OrderStatus.TRADE_FAIL.getJnStatus())
                || orderStatus.equals(OrderStatus.PARTNER_CENTER_CANCEL.getJnStatus())
                || orderStatus.equals(OrderStatus.JN_DELIVERY_CANCEL.getJnStatus());
        }
    }
}
