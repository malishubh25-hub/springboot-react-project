package com.baji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BajiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BajiApplication.class, args);
//	
//		
//		package com.octa.oms.controllers;
//
//		import org.springframework.beans.factory.annotation.Autowired;
//		import org.springframework.http.HttpStatus;
//		import org.springframework.http.ResponseEntity;
//		import org.springframework.web.bind.annotation.PostMapping;
//		import org.springframework.web.bind.annotation.RequestBody;
//		import org.springframework.web.bind.annotation.RequestMapping;
//		import org.springframework.web.bind.annotation.RestController;
//
//		import com.octa.oms.VND.OMSObject;
//		import com.octa.oms.VND.OMSVersion;
//		import com.octa.oms.global.bean.CommonResponseBean;
//		import com.octa.oms.order.service.IOrderPlaceService;
//		import com.octa.oms.orderPanel.bean.OrderPlaceBean;
//		import com.octa.oms.statemachine.OmsSuperStatemachine;
//
//		@OMSVersion("1.0.0")
//		@OMSObject(OrderPlaceController.class)
//		@RestController
//		@RequestMapping(path = "/api/orderPlace")
//		public class OrderPlaceController implements OmsSuperStatemachine{
//			
//			@Autowired IOrderPlaceService orderPlaceService;
//
//			@PostMapping("/saveOrder")
//			 public ResponseEntity<CommonResponseBean> saveOrderPlace(@RequestBody OrderPlaceBean orderPlaceBean) {
//			      return new ResponseEntity<>(orderPlaceService.saveOrderPlace(orderPlaceBean),HttpStatus.OK);
//			 }
//			
//			@PostMapping("/placeOrder")
//			 public ResponseEntity<CommonResponseBean> feOrderPlace(@RequestBody OrderPlaceBean orderPlaceBean) {
//			      return new ResponseEntity<>(orderPlaceService.feOrderPlace(orderPlaceBean),HttpStatus.OK);
//			 }
//			
//		}
//
//
//		
//		package com.octa.oms.order.service;
//
//		import com.octa.oms.global.bean.CommonResponseBean;
//		import com.octa.oms.orderPanel.bean.OrderPlaceBean;
//
//		public interface IOrderPlaceService {
//
//			CommonResponseBean saveOrderPlace(OrderPlaceBean orderPlaceBean);
//
//			CommonResponseBean feOrderPlace(OrderPlaceBean orderPlaceBean);
//
//		}
//
//	
//		package com.octa.oms.order.service.impl;
//
//		import java.math.BigDecimal;
//		import java.sql.Timestamp;
//		import java.time.Instant;
//		import java.time.LocalDate;
//		import java.util.ArrayList;
//		import java.util.List;
//
//		import org.springframework.beans.factory.annotation.Autowired;
//		import org.springframework.stereotype.Service;
//
//		import com.octa.oms.VND.OMSObject;
//		import com.octa.oms.checkout.entities.OmsCartSalesCart;
//		import com.octa.oms.common.utils.OmsUtils;
//		import com.octa.oms.global.bean.CommonResponseBean;
//		import com.octa.oms.order.entities.OmsOrHistoryStatus;
//		import com.octa.oms.order.entities.OmsOrOrder;
//		import com.octa.oms.order.entities.OmsOrSalesPayment;
//		import com.octa.oms.order.entities.OmsOrSuborder;
//		import com.octa.oms.order.entities.OmsProductPriceInventory;
//		import com.octa.oms.order.service.IOrderPlaceService;
//		import com.octa.oms.orderPanel.bean.CheckoutOrderBean;
//		import com.octa.oms.orderPanel.bean.OrderPlaceBean;
//		import com.octa.oms.orderPanel.bean.OrderSalesCartBean;
//		import com.octa.oms.orderPanel.bean.SubOrderDetailsBean;
//		import com.octa.oms.repositories.CartSalesCartRepository;
//		import com.octa.oms.repositories.OrderDetailRepository;
//		import com.octa.oms.repositories.OrderHistoryStatusRepository;
//		import com.octa.oms.repositories.ProductPriceInventoryRepository;
//		import com.octa.oms.repositories.SubOrderRepository;
//		import com.octa.oms.repositories.UserDetailRepository;
//		import com.octa.oms.user.entities.OmsUsrUserDetail;
//
//		import lombok.extern.slf4j.Slf4j;
//
//		@OMSObject(OrderPlaceService.class)
//		@Service
//		@Slf4j
//		public class OrderPlaceService implements IOrderPlaceService {
//			@Autowired
//			SubOrderRepository subOrderRepository;
//			@Autowired
//			UserDetailRepository userDetailRepository;
//			@Autowired
//			CartSalesCartRepository cartSalesCartRepository;
//			@Autowired
//			OrderDetailRepository orderDetailRepository;
//			@Autowired
//			OrderHistoryStatusRepository orderHistoryStatusRepository;
//			@Autowired
//			com.octa.oms.repositories.SalesPaymentOrderRepository salesPaymentOrderRepository;
//			@Autowired
//			ProductPriceInventoryRepository productPriceInventoryRepository;
//			public static final String BLANK = "";
//			public static final BigDecimal ZERO_DECIMAL = new BigDecimal("0.00");
//			public static final String INACTIVE = "N";
//
//			@Override
//			public CommonResponseBean saveOrderPlace(OrderPlaceBean orderPlaceBean) {
//				CommonResponseBean responseBean = new CommonResponseBean();
//				try {
//					OmsOrSuborder omsOrSuborder = mapBeanToEntity(orderPlaceBean);
//					if (OmsUtils.isNotEmpty(omsOrSuborder.getSuborderRfnum())) {
//						responseBean.setResponse(omsOrSuborder);
//						responseBean.setMessage("Order Placed Succesfully");
//						responseBean.setSuccess(true);
//					} else {
//						responseBean.setMessage("User Not Found");
//						responseBean.setResponse(omsOrSuborder);
//						responseBean.setSuccess(false);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return responseBean;
//			}
//
//			public OmsOrSuborder mapBeanToEntity(OrderPlaceBean orderPlaceBean) {
//				OmsOrSuborder omsOrSuborder = new OmsOrSuborder();
//				OmsOrOrder omsOrOrder = new OmsOrOrder();
//				try {
//					OmsUsrUserDetail ubi;
//					ubi = userDetailRepository.findByLoginIds(Long.parseLong(orderPlaceBean.getUserId()));
//					if (OmsUtils.isNotEmpty(ubi)) {
////					OmsCartSalesCart omsCartSalesCart = cartSalesCartRepository.getCart(orderPlaceBean.getPaymentMode());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderUserRfnum, ubi.getUserRfnum());
////						OmsUtils.setIfNotNull(omsOrOrder :: setOmsCartSalesCart ,1);
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderNumber, orderPlaceBean.getTotalSubOrder() + "");
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderUsrid, ubi.getUserRfnum() + "");
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderBuyerFname, ubi.getUserFname());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderBuyerMname, ubi.getUserMname());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderBuyerLname, ubi.getUserLname());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderBillMobile, ubi.getUserMobile());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderBillingEmail, ubi.getUserCommunicationEmail());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderBillAddr1, orderPlaceBean.getAdd1());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderBillAddr2, orderPlaceBean.getAdd2());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderBillAddr3, orderPlaceBean.getAdd3());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderBillCityname, orderPlaceBean.getCity());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderBillStatename, orderPlaceBean.getState());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderBillCountryname, orderPlaceBean.getCountry());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderBillzip, orderPlaceBean.getZipCode());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderChannel, "web");
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderStatusCode, "ORNEW");
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderStatusName, "Order Received");
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderSmdcode, "OCTA_DEF_S");
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderGrosscartprice, orderPlaceBean.getOrderTotalPayAmount());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderNetprice, orderPlaceBean.getOrderTotalPayAmount());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderPayamt, orderPlaceBean.getOrderTotalPayAmount());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderType, "General Order");
//						OmsUtils.setIfNotNull(omsOrOrder::setDeleted, "0");
//						OmsUtils.setIfNotNull(omsOrOrder::setOrgId, 0l);
//						OmsUtils.setIfNotNull(omsOrOrder::setModifiedBy, 0l);
//						OmsUtils.setIfNotNull(omsOrOrder::setCreatedBy, 0l);
//						OmsUtils.setIfNotNull(omsOrOrder::setModifiedbyname, ubi.getUserFname());
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderIscallcenter, "");
//						omsOrOrder = orderDetailRepository.save(omsOrOrder);
//						OmsUtils.setIfNotNull(omsOrOrder::setOrderNumber, omsOrOrder.getOrderRfnum() + "");
//						omsOrOrder = orderDetailRepository.save(omsOrOrder);
//						OmsOrHistoryStatus omsOrHistoryStatus = new OmsOrHistoryStatus();
//						if (OmsUtils.isNotEmpty(omsOrOrder)) {
//							OmsUtils.setIfNotNull(omsOrSuborder::setOmsOrOrder, omsOrOrder);
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderNumber, orderPlaceBean.getTotalSubOrder());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderSmdcode, omsOrOrder.getOrderSmdcode());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvFname, omsOrOrder.getOrderBuyerFname());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvMname, omsOrOrder.getOrderBuyerMname());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvLname, omsOrOrder.getOrderBuyerLname());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvMobile, orderPlaceBean.getMobileNo());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvEmail, orderPlaceBean.getEmailId());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvAddr1, orderPlaceBean.getAdd1());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvAddr2, orderPlaceBean.getAdd2());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvAddr3, orderPlaceBean.getAdd3());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvCityname, orderPlaceBean.getCity());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvStatename, orderPlaceBean.getState());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvCountryname, orderPlaceBean.getCountry());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvZip, orderPlaceBean.getZipCode());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderIssellerShipper, 'N');
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderProductcode, "1");
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderPilsku, "1");
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderPildesc, "test prod");
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderPilname, "testProd");
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderCategoryCode, "GLD000GTP");
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderCategoryName, "Ear Gold");
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderStatusCode, "ORNEW");
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderStatusName, "Order Received");
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderStatusRfnum, 55L);
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderQty,
//									Integer.parseInt(orderPlaceBean.getTotalSubOrder()));
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderTotalItemPrice,
//									orderPlaceBean.getOrderTotalPayAmount());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderGrossPrice,
//									orderPlaceBean.getOrderTotalPayAmount());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderNetItemPrice,
//									orderPlaceBean.getOrderTotalPayAmount());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderPayablePrice,
//									orderPlaceBean.getOrderTotalPayAmount());
//							OmsUtils.setIfNotNull(omsOrSuborder::setDeleted, "0");
//							OmsUtils.setIfNotNull(omsOrSuborder::setOrgId, 0l);
//							OmsUtils.setIfNotNull(omsOrSuborder::setModifiedBy, 0l);
//							OmsUtils.setIfNotNull(omsOrSuborder::setModifiedByName, "0");
//							OmsUtils.setIfNotNull(omsOrSuborder::setCreatedBy, 0l);
//							omsOrSuborder = subOrderRepository.save(omsOrSuborder);
//							OmsUtils.setIfNotNull(omsOrHistoryStatus::setOmsOrSubOrder, omsOrSuborder);
//							OmsUtils.setIfNotNull(omsOrHistoryStatus::setOmsOrOrder, omsOrOrder);
//							OmsUtils.setIfNotNull(omsOrHistoryStatus::setNewStatus, omsOrSuborder.getSuborderStatusName());
//							OmsUtils.setIfNotNull(omsOrHistoryStatus::setNewStatusCode, omsOrSuborder.getSuborderStatusCode());
//							orderHistoryStatusRepository.save(omsOrHistoryStatus);
//						}
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return omsOrSuborder;
//			}
//
//			@Override
//			public CommonResponseBean feOrderPlace(OrderPlaceBean orderPlaceBean) {
//				CommonResponseBean responseBean = new CommonResponseBean();
//				List<String> unavailableProducts = new ArrayList<>();
//				try {
//					boolean allProductsAvailable = true;
//					for (SubOrderDetailsBean subOrderDetailsBean : orderPlaceBean.getSubOrderList()) {
//						List<OmsProductPriceInventory> stockDeduction = productPriceInventoryRepository
//								.findByProductCodeNSkuCode(subOrderDetailsBean.getStoreCode(),
//										subOrderDetailsBean.getSubProductCode(), subOrderDetailsBean.getSellerCode(),
//										subOrderDetailsBean.getProductSku());
//						if (stockDeduction.get(0) == null
//								|| stockDeduction.get(0).getInvpriceQty() < subOrderDetailsBean.getSuborderqty()) {
//							allProductsAvailable = false;
//							unavailableProducts.add(subOrderDetailsBean.getProductSku());
//						}
//					}
//					if (allProductsAvailable) {
//						String message = placeCartOrder(orderPlaceBean, "N");
//						if (message.equalsIgnoreCase("success")) {
//							for (SubOrderDetailsBean subOrderDetailsBean : orderPlaceBean.getSubOrderList()) {
//								List<OmsProductPriceInventory> stockDeduction = new ArrayList<>();
//								stockDeduction = productPriceInventoryRepository.findByProductCodeNSkuCode(
//										subOrderDetailsBean.getStoreCode(), subOrderDetailsBean.getSubProductCode(),
//										subOrderDetailsBean.getSellerCode(), subOrderDetailsBean.getProductSku());
//								int actual = stockDeduction.get(0).getInvpriceQty() - subOrderDetailsBean.getSuborderqty();
//								stockDeduction.get(0).setInvpriceQty(actual);
//								productPriceInventoryRepository.save(stockDeduction.get(0));
//							}
//							responseBean.setResponse(orderPlaceBean);
//							responseBean.setMessage("Order Placed Successfully");
//							responseBean.setSuccess(true);
//						} else {
//							responseBean.setMessage("Order Placing Issue");
//							responseBean.setSuccess(false);
//						}
//					} else {
//						responseBean.setMessage("Some Products are unavailable, cannot place order. Unavailable products:"
//								+ unavailableProducts);
//						responseBean.setSuccess(false);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					responseBean.setMessage("Exception occurred while placing order");
//					responseBean.setSuccess(false);
//				}
//				return responseBean;
//			}
//
//			@SuppressWarnings("unchecked")
//			public String placeCartOrder(OrderPlaceBean orderPlaceBean, String isMobile) {
//				List<CheckoutOrderBean> orderBeanList = new ArrayList<CheckoutOrderBean>();
//				String response = "fail";
//				CheckoutOrderBean checkoutOrderBean = new CheckoutOrderBean();
//				try {
//					checkoutOrderBean.setPaymentMode(orderPlaceBean.getPaymentMode());
//					checkoutOrderBean.setOrderType("General Product");
//					OmsOrSalesPayment omsOrSalesPayment = null;
//					orderBeanList.add(checkoutOrderBean);
//					OrderSalesCartBean orderCartBean = new OrderSalesCartBean();
//					orderCartBean.setOrderTotalPayAmount(orderPlaceBean.getOrderTotalPayAmount().toString());
//					OmsCartSalesCart saleCartObj = generateOrderSalesCart(orderCartBean, orderPlaceBean);
//					saleCartObj = cartSalesCartRepository.save(saleCartObj);
//					if (OmsUtils.isNotEmpty(saleCartObj)) {
//						log.info("---------------WAY TO CREATE ORDER IN DB FROM ORDER BEAN----------------");
//					}
//					if (OmsUtils.isNotEmpty(orderBeanList)) {
//						response = generateOrders(orderPlaceBean);
//						omsOrSalesPayment = generateOrderSalesPayment(orderPlaceBean.getPaymentMode(),
//								orderPlaceBean.getOrderTotalPayAmount(), orderPlaceBean.getUserId());
//						if (OmsUtils.isNotEmpty(response.equalsIgnoreCase("success"))) {
//							log.info("---------------Now Going Ahead to perform Payment----------------");
//							afterOrderCallBackAction(omsOrSalesPayment);
//						}
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return response;
//			}
//
//			public OmsCartSalesCart generateOrderSalesCart(OrderSalesCartBean orderCartBean, OrderPlaceBean orderPlaceBean) {
//				log.info("---------------Creating Order Sales Cart----------------");
//				;
//				OmsCartSalesCart octaSalesCart = new OmsCartSalesCart();
//				try {
//					OmsUtils.setIfNotNull(octaSalesCart::setSalesNetCartPrice, orderPlaceBean.getOrderTotalPayAmount());
//					octaSalesCart.setSalesGrossCartPrice(orderPlaceBean.getOrderTotalPayAmount());
//
//					octaSalesCart.setSalesPaymentCode(orderPlaceBean.getPaymentMode());
//					octaSalesCart.setSalesPaymentName(orderCartBean.getOrderPaymentName());
////					OmsUtils.setIfNotNull(octaSalesCart :: setSalesShipcharge ,orderCartBean.getShipcharge().toString());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return octaSalesCart;
//			}
//
//			public void afterOrderCallBackAction(OmsOrSalesPayment octaSalesPaymt) {
//				try {
//					if (OmsUtils.isNotEmpty(octaSalesPaymt)) {
//						OmsCartSalesCart octaSalesCart = octaSalesPaymt.getOmsCartSalesCart();
////						OmsOrOrder pot =orderDetailRepository.getCartData(octaSalesCart.getSalesRfnum());
//						if (OmsUtils.isNotEmpty(octaSalesPaymt.getSalesPaymentRfnum())) {
//							String isOnline = "Y";
//							if (isOnline != null && "Y".equalsIgnoreCase(isOnline)
//									&& octaSalesPaymt.getSalesPaymentCode().equalsIgnoreCase("PMCO")) {
//								log.info("Cart Pay Amount:::" + octaSalesPaymt.getSalesPayAmout());
//								if (octaSalesPaymt.getSalesPayAmout().compareTo(new BigDecimal("0.0")) == 0
//										|| octaSalesPaymt.getSalesPayAmout().compareTo(new BigDecimal("0.0")) == -1) {
////										BigDecimal couponValue  = OmsUtils.isNotEmpty(octaSalesCart.getSalesDcamt()) ? octaSalesPaymt.getSalesDcamt() :BigDecimal.ZERO ;
////										BigDecimal wallAmt = OmsUtils.isNotEmpty(octaSalesCart.getSalesWalletAmount()) ?octaSalesPaymt.getSalesWalletAmount() :BigDecimal.ZERO ;
//
//								} else {
////										if(pot!=null && !pot.getPotorderchannel().equalsIgnoreCase(OmsConstants.CHANNEL_MOBILEORDER)) {
////											new DefaultAsyncPaymentIntegratorEngineImpl().processPayment(octaSalesPaymt.getSalesPaymentRfnum().toString(),octaSalesPaymt.getSalesPayAmout().toString(),octaSalesPaymt.getSalesPaymentCode(),null);
////										}
//								}
////									req.setAttribute("page", null);
//							} else {
////									req.setAttribute("page", "thankyou");
//								if (octaSalesPaymt.getSalesPayAmout().compareTo(new BigDecimal("0.0")) == 0
//										|| octaSalesPaymt.getSalesPayAmout().compareTo(new BigDecimal("0.0")) == -1) {
////										BigDecimal couponValue  = OmsUtils.isNotEmpty(octaSalesPaymt.getSalesPayAmout()) ? octaSalesPaymt.getSalesDcamt() :BigDecimal.ZERO ;
////										BigDecimal wallAmt = OmsUtils.isNotEmpty(octaSalesPaymt.getw()) ? octaSalesPaymt.getSalesWalletAmount() :BigDecimal.ZERO ;
//
//								}
//							}
//						}
//					}
//				} catch (Exception e) {
//					StackTraceElement ste = e.getStackTrace()[0];
//					e.printStackTrace();
//					log.error("Message :: " + e.toString() + " Cause :: " + e.getCause() + " Detail ::" + ste.getClassName()
//							+ ">" + ste.getMethodName() + ">" + ste.getLineNumber());
//				}
//			}
//
//			public OmsOrSalesPayment generateOrderSalesPayment(String payMode, BigDecimal payAmount, String userId) {
//				OmsOrSalesPayment omsOrSalesPayment = new OmsOrSalesPayment();
//				try {
//					LocalDate currentDate = LocalDate.now();
//					Timestamp timestamp = Timestamp.valueOf(currentDate.atStartOfDay());
//					if (OmsUtils.isNotEmpty(omsOrSalesPayment)) {
//						omsOrSalesPayment.setSalesPaymentCode(payMode);
//						omsOrSalesPayment.setSalesPaymentName(payMode);
//						omsOrSalesPayment.setSalesPayAmout(payAmount);
//						omsOrSalesPayment.setIsTrxnSuccess("N");
//						omsOrSalesPayment.setCreateDate(timestamp);
//						omsOrSalesPayment.setModifieDate(timestamp);
//						omsOrSalesPayment.setDeleted("N");
//						omsOrSalesPayment.setOrgId(0l);
//						if (OmsUtils.isNotEmpty(userId)) {
//							omsOrSalesPayment.setCreatedBy(new Long(userId));
//							omsOrSalesPayment.setModifiedBy(new Long(userId));
//							omsOrSalesPayment.setModifiedByName("");
//						} else {
//							omsOrSalesPayment.setCreatedBy(0L);
//							omsOrSalesPayment.setModifiedBy(0L);
//						}
//						salesPaymentOrderRepository.save(omsOrSalesPayment);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return omsOrSalesPayment;
//			}
//
//			public String generateOrders(OrderPlaceBean orderPlaceBean) {
//				String status = "success";
//				Long orderId = null;
//				OmsOrOrder omsOrOrder = null;
//				List<OmsOrSuborder> subOrderList = new ArrayList<OmsOrSuborder>();
//				List<Long> subOrderAttrList = new ArrayList<Long>();
//				log.info("---------------START TO CREATE MAIN ORDER IN DB ----------------");
//				omsOrOrder = generateOrder(orderPlaceBean, orderId);
//				omsOrOrder = orderDetailRepository.save(omsOrOrder);
//				OmsUtils.setIfNotNull(omsOrOrder::setOrderNumber, "" + omsOrOrder.getOrderRfnum() + "");
//				omsOrOrder = orderDetailRepository.save(omsOrOrder);
//				orderPlaceBean.setOrderId(omsOrOrder.getOrderNumber());
//				orderPlaceBean.setOrderDate(omsOrOrder.getOrderDate());
//				log.info("---------------END TO CREATE MAIN ORDER IN DB ----------------");
//				if (omsOrOrder != null && omsOrOrder.getOrderRfnum() != null) {
//					log.info("---------------START TO CREATE SUBORDER ORDER IN DB ----------------");
//					status = generateSubOrder(omsOrOrder, subOrderList, subOrderAttrList, orderPlaceBean);
//
//					log.info("---------------END TO CREATE SUBORDER ORDER IN DB ----------------");
//				}
//				return status;
//			}
//
//			public String generateSubOrder(OmsOrOrder omsOrOrder, List<OmsOrSuborder> subOrderList, List<Long> subOrderAttrList,
//					OrderPlaceBean orderPlaceBean) {
//				String status = "success";
//				try {
//					if (orderPlaceBean != null && orderPlaceBean.getSubOrderList() != null) {
//						for (SubOrderDetailsBean subOrderDetailsBean : orderPlaceBean.getSubOrderList()) {
//							OmsOrSuborder omsOrSuborder = new OmsOrSuborder();
//							BigDecimal totalOrderAmount = BigDecimal.ZERO;
//							BigDecimal qty = new BigDecimal(subOrderDetailsBean.getSuborderqty());
//							totalOrderAmount = subOrderDetailsBean.getSubTotalPayAmount();
//							omsOrSuborder.setSuborderIscommission('N');
//							OmsUtils.setIfNotNull(omsOrSuborder::setPstClgId,orderPlaceBean.getCatalogue());
//							OmsUtils.setIfNotNull(omsOrSuborder::setOmsOrOrder, omsOrOrder);
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderNumber, 1 + "");
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderSmdcode, omsOrOrder.getOrderSmdcode());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvFname, omsOrOrder.getOrderBuyerFname());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvMname, omsOrOrder.getOrderBuyerMname());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvLname, omsOrOrder.getOrderBuyerLname());
//							c.setIfNotNull(omsOrSuborder::setSuborderRecvMobile,
//									subOrderDetailsBean.getOrderbillmobile());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvEmail, omsOrOrder.getOrderBillingEmail());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvAddr1, subOrderDetailsBean.getOrderbilladdr1());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvAddr2, subOrderDetailsBean.getOrderbilladdr2());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvAddr3, subOrderDetailsBean.getOrderbilladdr3());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvCityname,
//									subOrderDetailsBean.getOrderbillcityname());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvStatename,
//									subOrderDetailsBean.getOrderbillstatename());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvCountryname,
//									subOrderDetailsBean.getOrderbillcountryname());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderRecvZip, subOrderDetailsBean.getOrderbillzip());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderIssellerShipper, 'N');
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderProductcode,
//									subOrderDetailsBean.getSubProductCode());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderPilsku, subOrderDetailsBean.getProductSku());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderPildesc, subOrderDetailsBean.getProductDesc());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderPilname, subOrderDetailsBean.getProductName());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderCategoryCode,
//									subOrderDetailsBean.getCategoryCode());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderCategoryName,
//									subOrderDetailsBean.getCategoryName());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderStatusCode, "ORDCRET");
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderStatusName, "Order Created");
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderStatusRfnum, 55L);
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderTotalItemPrice, totalOrderAmount);
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderGrossPrice, totalOrderAmount);
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderNetItemPrice, totalOrderAmount);
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderPayablePrice, totalOrderAmount);
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderQty,Integer.valueOf(subOrderDetailsBean.getSuborderqty()));
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderCartPromocode,
//									subOrderDetailsBean.getSubEcomPromoCode());
//							OmsUtils.setIfNotNull(omsOrSuborder::setDeleted, "0");
//							OmsUtils.setIfNotNull(omsOrSuborder::setOrgId, 0l);
//							OmsUtils.setIfNotNull(omsOrSuborder::setModifiedBy, Long.parseLong(orderPlaceBean.getUserId()));
//							OmsUtils.setIfNotNull(omsOrSuborder::setModifiedByName, orderPlaceBean.getUserId());
//							OmsUtils.setIfNotNull(omsOrSuborder::setCreatedBy, Long.parseLong(orderPlaceBean.getUserId()));
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderSellerCode, subOrderDetailsBean.getSellerCode());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderStoreName, subOrderDetailsBean.getStoreCode());
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderPilsku, subOrderDetailsBean.getProductSku());
//							OmsUtils.setIfNotNull(omsOrSuborder::setPstProdImg, subOrderDetailsBean.getSuborderProdImg());
//							OmsUtils.setIfNotNull(omsOrSuborder::setPstProdId, subOrderDetailsBean.getProductId());
//							omsOrSuborder = subOrderRepository.save(omsOrSuborder);
//							OmsUtils.setIfNotNull(omsOrSuborder::setSuborderNumber, omsOrSuborder.getSuborderRfnum() + "");
//							subOrderRepository.save(omsOrSuborder);
//							subOrderDetailsBean.setSubOrderId(omsOrSuborder.getSuborderRfnum().toString());
//							orderPlaceBean.setOrderDate(omsOrOrder.getOrderDate());
//							subOrderList.add(omsOrSuborder);
//							// Status History save
//							OmsOrHistoryStatus omsOrHistoryStatus = new OmsOrHistoryStatus();
//							OmsUtils.setIfNotNull(omsOrHistoryStatus::setOmsOrSubOrder, omsOrSuborder);
//							OmsUtils.setIfNotNull(omsOrHistoryStatus::setOmsOrOrder, omsOrOrder);
//							OmsUtils.setIfNotNull(omsOrHistoryStatus::setNewStatus, omsOrSuborder.getSuborderStatusName());
//							OmsUtils.setIfNotNull(omsOrHistoryStatus::setNewStatusCode, omsOrSuborder.getSuborderStatusCode());
//							orderHistoryStatusRepository.save(omsOrHistoryStatus);
//						}
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					status = "Problems in generating Sub Order [" + e.getMessage() + "]";
//				}
//				return status;
//			}
//
//			public OmsOrOrder generateOrder(OrderPlaceBean orderBeanList, Long orderId) {
//				OmsOrOrder omsOrOrder = new OmsOrOrder();
//				String status = "success";
//				try {
//					BigDecimal totalOrderAmount = BigDecimal.ZERO;
//					for (SubOrderDetailsBean subOrderDetailsBean : orderBeanList.getSubOrderList()) {
//						BigDecimal qty = new BigDecimal(subOrderDetailsBean.getSuborderqty());
//						BigDecimal subtotal = subOrderDetailsBean.getSubTotalPayAmount();
//						totalOrderAmount = totalOrderAmount.add(subtotal);
//					}
//					omsOrOrder.setOrderAltMobile(BLANK);
//					OmsUtils.setIfNotNull(omsOrOrder::setClgId, orderBeanList.getCatalogue());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderBuyerFname, orderBeanList.getFName());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderBuyerMname, orderBeanList.getMName());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderBuyerLname, orderBeanList.getLName());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderBillMobile, orderBeanList.getMobileNo());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderBillingEmail, orderBeanList.getEmailId());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderBillAddr1, orderBeanList.getAdd1());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderBillAddr2, orderBeanList.getAdd2());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderBillAddr3, orderBeanList.getAdd3());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderLandmark, orderBeanList.getLandmark());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderBillCityname, orderBeanList.getCity());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderBillStatename, orderBeanList.getState());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderBillCountryname, orderBeanList.getCountry());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderBillzip, orderBeanList.getZipCode());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderBillPhone, orderBeanList.getPhone());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderAltMobile, orderBeanList.getMobileNo());
//					omsOrOrder.setDeleted(OrderPlaceService.INACTIVE);
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderNumber, 1 + "");
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderUsrid, orderBeanList.getUserId() + "");
//					Timestamp orderDate = orderBeanList.getOrderDate() != null ? orderBeanList.getOrderDate()
//							: Timestamp.from(Instant.now());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderDate, orderDate);
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderChannel, "web");
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderPmtname, orderBeanList.getPaymentMode());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderStatusCode, "ORDCRET");
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderStatusName, "Order Created");
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderSmdcode, "OCTA_DEF_S");
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderGrosscartprice, totalOrderAmount);
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderNetprice, totalOrderAmount);
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderPayamt, totalOrderAmount);
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderType, "General Order");
//					OmsUtils.setIfNotNull(omsOrOrder::setOrgId, 0l);
//					OmsUtils.setIfNotNull(omsOrOrder::setModifiedBy, Long.parseLong(orderBeanList.getUserId()));
//					OmsUtils.setIfNotNull(omsOrOrder::setCreatedBy, Long.parseLong(orderBeanList.getUserId()));
//					OmsUtils.setIfNotNull(omsOrOrder::setModifiedbyname, orderBeanList.getFName());
//					OmsUtils.setIfNotNull(omsOrOrder::setOrderIscallcenter, "");
//				} catch (Exception e) {
//					e.printStackTrace();
//					status = "Problems in generating Sub Order [" + e.getMessage() + "]";
//				}
//				return omsOrOrder;
//			}
//
//		}
//		
//
//		/**
//		 * 
//		 */
//		package com.octa.oms.repositories;
//
//		import java.util.List;
//
//		import org.springframework.data.jpa.repository.JpaRepository;
//		import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//		import org.springframework.data.jpa.repository.Query;
//		import org.springframework.data.repository.query.Param;
//		import org.springframework.stereotype.Repository;
//
//		import com.octa.oms.VND.OMSObject;
//		import com.octa.oms.global.OmsSuperGlobal;
//		import com.octa.oms.order.entities.OmsOrSuborder;
//
//		/**
//		 * @author Kunal
//		 *
//		 */
//		@OMSObject(SubOrderRepository.class)
//		@Repository
//		public interface SubOrderRepository extends JpaRepository<OmsOrSuborder, Long>,JpaSpecificationExecutor<OmsOrSuborder>,OmsSuperGlobal {
//			
//			
//			@Query("from OmsOrSuborder pst where pst.omsOrOrder.orderRfnum=:orderRefnum")
//			public OmsOrSuborder findByPotrfNum(@Param("orderRefnum") Long orderRefnum);
//			
//			@Query("FROM OmsOrSuborder pst WHERE pst.omsOrOrder.orderRfnum=:orderRefnum and pst.suborderStatusCode NOT IN ('CANCCUST','CANCMP')")
//			public List<OmsOrSuborder> findByPotForOrder(@Param("orderRefnum") Long orderRefnum);
//			
//			@Query("FROM OmsOrSuborder pst WHERE pst.omsOrOrder.orderRfnum=:orderRefnum and pst.suborderStatusCode IN ('CANCCUST','CANCMP')")
//			public List<OmsOrSuborder> findByPotForCanOrder(@Param("orderRefnum") Long orderRefnum);
//			 
//			@Query("from OmsOrSuborder pst where pst.omsOrOrder.orderRfnum=:orderRefnum")
//			public List<OmsOrSuborder> findByPot(@Param("orderRefnum") Long orderRefnum);
//			
//			@Query("select count(*) from OmsOrSuborder pst where pst.omsOrOrder.orderRfnum=:orderRefnum and pst.suborderStatusCode= :statusCode")
//			public Integer findCountPstByPotrfnumByStatus(@Param("orderRefnum") Long orderRefnum,@Param("statusCode") String statusCode);
//			
//			@Query("SELECT CASE WHEN COUNT(pst) > 0 THEN true ELSE false END FROM OmsOrSuborder pst WHERE pst.omsOrOrder.orderRfnum = :omsOrOrder AND pst.suborderRfnum = :suborderRfnum")
//		    boolean existsSuborderRfnumAndOmsOrOrder(Long suborderRfnum, Long omsOrOrder);
//			
//			//public Page<OmsOrSuborder> findAll(CriteriaQuery<OmsOrSuborder> query, Pageable pageable);
//			
//			@Query("SELECT obj FROM OmsOrSuborder obj WHERE "
//					+"(:orderRfnum is null or obj.omsOrOrder.orderRfnum = :orderRfnum) and "
//					+"(:subOrderNo is null or obj.suborderNumber = :subOrderNo)")
//			OmsOrSuborder findByStatus(@Param("orderRfnum") Long orderRfnum,@Param("subOrderNo") String subOrderNo);
//			
//			@Query("SELECT obj FROM OmsOrSuborder obj WHERE "
//					+"(:subOrderNo is null or obj.suborderRfnum = :subOrderNo)")
//			OmsOrSuborder findByStatus(@Param("subOrderNo") Long subOrderNo);
//			
//			@Query("from OmsOrSuborder pst where pst.omsOrOrder.orderUsrid=:orderRefnum and pst.omsOrOrder.orderType=:orderType and pst.pstClgId=:clg order by createDate DESC")
//			 List<OmsOrSuborder> findByUserOrderAndCartType(@Param("orderRefnum") String orderRefnum, @Param("orderType") String orderType, @Param("clg") Long clg);
//			
//			}
//
//		
//		package com.octa.oms.order.entities;
//
//		import java.math.BigDecimal;
//		import java.sql.Date;
//		import java.sql.Timestamp;
//
//		import org.hibernate.annotations.CreationTimestamp;
//		import org.hibernate.annotations.UpdateTimestamp;
//
//		import com.fasterxml.jackson.annotation.JsonBackReference;
//		import com.fasterxml.jackson.annotation.JsonInclude;
//		import com.fasterxml.jackson.annotation.JsonInclude.Include;
//
//		import jakarta.persistence.Column;
//		import jakarta.persistence.Entity;
//		import jakarta.persistence.GeneratedValue;
//		import jakarta.persistence.GenerationType;
//		import jakarta.persistence.Id;
//		import jakarta.persistence.JoinColumn;
//		import jakarta.persistence.ManyToOne;
//		import jakarta.persistence.NamedQuery;
//		import jakarta.persistence.Table;
//		import jakarta.persistence.Temporal;
//		import jakarta.persistence.TemporalType;
//		import lombok.Data;
//
//		/**
//		 * @author shivani
//		 *
//		 */
//
//
//		/**
//		 * The persistent class for the OMS_OR_PANEL_SUBORDER(Octapst) database table.
//		 * 
//		 */
//		@Entity
//		@Table(name="OMS_OR_SUBORDER")
//		@NamedQuery(name="OmsOrSuborder.findAll", query="SELECT o FROM OmsOrSuborder o")
//		@Data
//		@JsonInclude(Include.NON_NULL)
//		public class OmsOrSuborder {
//
//			@Id
//			@GeneratedValue(strategy=GenerationType.IDENTITY)
//			@Column(name="SUBORDER_RFNUM")
//			private Long suborderRfnum;
//
//			@Column(name="CREATEDATE")
//			@CreationTimestamp
//			private Timestamp createDate;
//
//			@Column(name="CREATEDDATENEW")
//			private String createdDatenew;
//
//			@Column(name="DELETED", nullable = false)
//			private String deleted;
//
//			@Column(name="MODIFIEDATE")
//			@UpdateTimestamp
//			private Timestamp modifieDate;
//
//			@Column(name="MODIFIEDBY", nullable = false)
//			private Long modifiedBy;
//
//			@Column(name="MODIFIEDBYNAME", nullable = false)
//			private String modifiedByName;
//
//			@Column(name="SUBORDER_ADDONCHARGE",precision = 10, scale = 2)
//			private BigDecimal suborderAddoncharge;
//
//			@Column(name="SUBORDER_ADDRESS_UPDATED")
//			private String suborderAddressUpdated;
//
//			@Column(name="SUBORDER_ALT_ADDR1")
//			private String suborderAltAddr1;
//
//			@Column(name="SUBORDER_ALT_ADDR2")
//			private String suborderAltAddr2;
//
//			//bi-directional many-to-one association to OmsOrPanelOrder
//			@ManyToOne
//			@JsonBackReference
//			@JoinColumn(name="SUBORDER_ORDER_FK", nullable = false)
//			private OmsOrOrder omsOrOrder;
//			
//			@Column(name="B2B_B2C")
//			private String b2bb2c;
//		}

//
//
//				package com.octa.oms.orderPanel.bean;
//
//		import java.math.BigDecimal;
//		import java.sql.Timestamp;
//		import java.util.List;
//
//		import com.fasterxml.jackson.annotation.JsonInclude;
//		import com.fasterxml.jackson.annotation.JsonInclude.Include;
//
//		import lombok.Data;
//
//		@Data
//		@JsonInclude(Include.NON_NULL)
//		public class OrderPlaceBean {
//			
//			 private String userId;
//			 private String totalSubOrder;
//			 private Timestamp orderDate;
//			 private BigDecimal orderTotalPayAmount;
//			 private String paymentMode;
//			 private String fName;
//			 private String mName; 
//			 private String lName;
//			 private String phone;
//			 private String emailId;
//			 private String mobileNo;
//			 private String add1;
//			 private String add2;
//			 private String add3;
//			 private String city;
//			 private String state;
//			 private String country;
//			 private String zipCode;
//			 private String landmark; 
//			 private String orderId;
//			 private String orderType;
//			 private String cartType;
//			 private Long catalogue;
//			 private String isBulkBuying;
//			 
//				private List<OrderStatusHistoryBean> orderStatusHistoryList=null;
//				private List<SubOrderDetailsBean> subOrderList=null;
//
//		}
//
//		
//
//		package com.octa.oms.common.utils;
//
//		import java.io.File;
//		import java.lang.reflect.Method;
//		import java.math.BigDecimal;
//		import java.time.LocalDate;
//		import java.util.Arrays;
//		import java.util.Calendar;
//		import java.util.Collection;
//		import java.util.Date;
//		import java.util.List;
//		import java.util.Map;
//		import java.util.Objects;
//		import java.util.Random;
//		import java.util.function.Consumer;
//		import java.util.stream.Collectors;
//
//		import org.apache.commons.lang.RandomStringUtils;
//		import org.apache.commons.lang.StringUtils;
//		import org.apache.commons.lang.WordUtils;
//
//		import com.octa.oms.VND.OMSObject;
//		import com.octa.user.utils.Constants;
//
//		import lombok.extern.slf4j.Slf4j;
//
//		@Slf4j
//		@OMSObject(OmsUtils.class)
//		public class OmsUtils {
//			 private static String input;
//				private static int num;
//				
//				private static String[] units ={ "", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine" };
//				
//				private static String[] teen = { " Ten", " Eleven", " Twelve", " Thirteen"," Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen"," Nineteen" };
//				
//				private static String[] tens = { " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety" };
//				
//				private static String[] maxs = { "", "", " Hundred", " Thousand", " Lakh", " Crore" };
//			
//			public static boolean isNotEmpty(Object value) {
//				if (value == null) {
//					return false;
//				}
//				if (value instanceof String) {
//					String text = (String) value;
//					return !text.trim().isEmpty() && !"null".equalsIgnoreCase(text.trim());
//				}
//				if (value instanceof Collection) {
//					return !((Collection<?>) value).isEmpty();
//				}
//				if (value instanceof Map) {
//					return !((Map<?, ?>) value).isEmpty();
//				}
//				if (value instanceof Object[]) {
//					return ((Object[]) value).length > 0;
//				}
//				if (value instanceof StringBuffer || value instanceof StringBuilder) {
//					String text = value.toString();
//					return !text.trim().isEmpty() && !"null".equalsIgnoreCase(text.trim());
//				}
//				return true;
//			}
//			
//			public static String concatenateNonNullStrings(String... strings) {
//		        return Arrays.stream(strings)
//		                .filter(Objects::nonNull)
//		                .collect(Collectors.joining(" "));
//		    }
//
//
//			public static boolean isEmpty(Object value) {
//				return !isNotEmpty(value);
//			}
//
//			public static String generateRandomChars(String candidateChars, int length) {
//				StringBuilder sb = new StringBuilder();
//				Random random = new Random();
//				for (int i = 0; i < length; i++) {
//					sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
//				}
//				return sb.toString();
//			}
//
//			public static String randomString() {
//				return RandomStringUtils.random(8);
//			}
//
//			public static String randomAlphabetic() {
//				return RandomStringUtils.randomAlphabetic(1);
//			}
//
//			public static String randomValue() {
//				return RandomStringUtils.randomAlphanumeric(8);
//			}
//
//			public static String randomNumericValue(int digitCount) {
//				return RandomStringUtils.randomNumeric(digitCount);
//			}
//
//			public static String generateAlphaNumericPassword() {
//				int leftLimit = 97; // letter 'a'
//				int rightLimit = 122; // letter 'z'
//				int targetStringLength = 10;
//				StringBuilder buffer = new StringBuilder(targetStringLength);
//				for (int i = 0; i < targetStringLength; i++) {
//					int randomLimitedInt = leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
//					buffer.append((char) randomLimitedInt);
//				}
//				String generatedString = buffer.toString();
//				return generatedString;
//			}
//
//			public static boolean isVendor(String adminType) {
//				boolean isVendor = false;
//				if (OmsUtils.isNotEmpty(adminType)) {
//					if (!OmsUtils.isStrEqualS(Constants.ADMIN_TYPE, adminType)
//							&& !OmsUtils.isStrEqualS(Constants.SUPERADMIN_TYPE, adminType)
//							&& !OmsUtils.isStrEqualS(Constants.OCTAADMIN_TYPE, adminType)) {
//						isVendor = true;
//					}
//				}
//				return isVendor;
//			}
//
//			public static boolean isStore(String adminType){
//				if(isNotEmpty(adminType) && isStrEqualS(adminType, Constants.STORE_TYPE)){
//					return true;
//				}
//				return false;
//			}
//
//			public static boolean isStrEqualS(String first, String second) {
//				first = first != null ? first : "";
//				second = second != null ? second : "";
//
//				if (first.equalsIgnoreCase(second)) {
//					return true;
//				} else {
//					return false;
//				}
//			}
//
//			public static void processCodes(String codes, List<String> codeList) {
//				try {
//					String[] codeSplit = codes.split(",");
//					for (int i = 0; i < codeSplit.length; i++) {
//						if (isNotEmpty(codeSplit[i])) {
//							codeList.add(codeSplit[i].trim());
//						}
//					}
//				} catch (Exception e) {
//					log.info("Cause " + e.getCause() + " Message " + e.getMessage() + " Method Name "
//							+ e.getStackTrace()[0].getMethodName() + " Line Number" + e.getStackTrace()[0].getLineNumber());
//				}
//			}
//
//			public static void invokeSetProperty(Object obj, String prop, Object paramValue) throws Exception {
//				String upperProp = prop.isEmpty() ? "" : Character.toUpperCase(prop.charAt(0)) + prop.substring(1);
//				Method method = obj.getClass().getMethod("set" + upperProp, paramValue.getClass());
//				if (method != null)
//					method.invoke(obj, paramValue);
//				else
//					throw new NoSuchMethodException("No such method: set" + upperProp);
//			}
//
//			public static String invokeGetProperty(Object obj, String prop) throws Exception {
//				String mname = "get" + prop;
//				Class<?>[] types = new Class[] {};
//				Method method = obj.getClass().getMethod(mname, types);
//				Object result = method.invoke(obj, new Object[0]);
//				String value = (String) result;
//				return value;
//			}
//
//			public static String printBulkUploadTimeEstimation(long startTIme, long endTime, int countDone, int totalCount) {
//				String estMsg = "";
//				try {
//					long processingtime = endTime - startTIme;
//					long singleProcessTime = processingtime / countDone;
//					long totalEstimatedSec = (singleProcessTime / 1000) * (long) (totalCount - countDone);
//					long totalEstimatedMin = 0L, totalEstimatedHr = 0L;
//
//					if (totalEstimatedSec > 60) {
//						totalEstimatedMin = totalEstimatedSec / 60;
//						totalEstimatedSec = totalEstimatedSec % 60;
//					}
//					if (totalEstimatedMin > 60) {
//						totalEstimatedHr = totalEstimatedMin / 60;
//						totalEstimatedMin = totalEstimatedMin % 60;
//					}
//					estMsg = " Batch Job Estimated Completion time : HH:MM:SS -> "
//							+ (totalEstimatedHr < 10 ? "0" + totalEstimatedHr : totalEstimatedHr) + ":"
//							+ (totalEstimatedMin < 10 ? "0" + totalEstimatedMin : totalEstimatedMin) + ":" + totalEstimatedSec;
//				} catch (Exception e) {
//					log.info("Cause " + e.getCause() + " Message " + e.getMessage() + " Method Name "
//							+ e.getStackTrace()[0].getMethodName() + " Line Number" + e.getStackTrace()[0].getLineNumber());
//				}
//				return estMsg;
//			}
//
//			public static boolean isValidDate(String date) {
//				try {
//					LocalDate.parse(date);
//				} catch (Exception e) {
//					System.out.println("Invalid date: " + date);
//					return false;
//				}
//				return true;
//			}
//
//			public static boolean isListEmpty(List list) {
//				return isEmpty(list);
//			}
//
//			public static boolean isNumeric(String str) {
//				return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
//			}
//
//			public static boolean isFileExist(String filePath) {
//				boolean isExist = false;
//				try {
//					File file = new File(filePath);
//					if (file.exists() && !file.isDirectory()) {
//						isExist = true;
//					}
//				} catch (Exception e) {
//					log.info("Cause " + e.getCause() + " Message " + e.getMessage() + " Method Name "
//							+ e.getStackTrace()[0].getMethodName() + " Line Number" + e.getStackTrace()[0].getLineNumber());
//				}
//				return isExist;
//			}
//
//			public static boolean isDecimalPoint(String str) {
//				return str.matches("^\\d+\\.\\d+"); // match a number with decimal point
//			}
//
//			public static boolean isDecimal(String str) {
//				return StringUtils.isNumeric(str);
//			}
//
//			public static boolean isDigitOnly(String str) {
//				return str.matches("\\d+"); // match a number with only Digits.
//			}
//
//			public static boolean isMapEmpty(Map map) {
//				if (map == null || map.isEmpty() || map.size() == 0 ){
//					return true;
//				}else{
//					return false;
//				}
//			}
//			public static boolean isKeyEmpty(Map map,String key) {
//				if (isMapEmpty(map)  || isEmpty(key)){
//					return true;
//				}else if(!isEmpty(key) && !isMapEmpty(map) &&  (!map.containsKey(key) || isEmpty(map.get(key)))){
//					return true;
//				}
//				else{
//					return false;
//				}
//			}
//
//			public static String getSingleQoatedString(String value){
//				StringBuilder sb = new StringBuilder("");
//				if(OmsUtils.isNotEmpty(value)){
//					String strArr[] = value.split(",");
//					for(String n:strArr){
//						if(sb.length() > 0) sb.append(',');
//						sb.append("'").append(n).append("'");
//					}
//				}
//				return sb.toString();
//			}
//
//			public static String getAddressDetails(String a1,String a2, String a3, String ct, String st, String zip){
//				String dtl = Constants.BLANK;
//				dtl = dtl.trim() + (isNotEmpty(a1) ? " "+a1 : "");
//				dtl = dtl.trim() + (isNotEmpty(a2) ? " "+a2 : "");
//				dtl = dtl.trim() + (isNotEmpty(a2) ? "\n "+a3 : "");
//				dtl = dtl.trim() + (isNotEmpty(ct) ? "\n "+ct : "");
//				dtl = dtl.trim() + (isNotEmpty(st) ? " "+st : "");
//				dtl = dtl.trim() + (isNotEmpty(zip) ? " - "+zip : "");
//
//				return dtl;
//			}
//
//			public static String prepareName(String fn,String mn, String ln){
//				String nm="";
//				if(isNotEmpty(fn)) nm = nm.trim() + fn.toUpperCase();
//				if(isNotEmpty(mn)) nm = nm.trim() +" "+ mn.toUpperCase();
//				if(isNotEmpty(ln)) nm = nm.trim() +" "+ ln.toUpperCase();
//				if(nm.length()>0) nm = nm.replace("  ", " ").trim();
//				return nm;
//			}
//
//			public static boolean isObjectEmpty(Object param) {
//				if (param == null){
//					return true;
//				}else{
//					return false;	
//				}
//			}
//			public static Date  getDateSubstractDays(Date date, int days){
//				Calendar calendar = Calendar.getInstance();
//				calendar.setTime(date);
//				calendar.add(Calendar.DATE, -days);
//				return calendar.getTime();
//			}
//
//			public static double roundOff(BigDecimal amount) {
//				double val=0;
//				try {
//					int n=amount.intValue();
//					if(n%10==5) {
//						val=n+5;
//					}else if(n%10>5 || n%10==0) {
//						val=(n + 4) / 5 * 5;
//					}else {
//						val=(n - 1) / 5 * 5;
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					log.error(e.getMessage());
//				}
//				return val;
//			}
//			public static String capitalizeFully(String text){
//				return WordUtils.capitalizeFully(text);
//			}	
//
//			public static String trimString(String variableName) {
//				byte[] arr = variableName.getBytes();
//				for (int i = 0; i < arr.length; i++) {
//					if (arr[i] == -96) {
//						arr[i] = ' ';
//					}
//				}
//				variableName = new String(arr);
//				return variableName.trim();
//			}
//
//			// Helper method to set a property if the value is not null
//			public static <T> void setIfNotNull(Consumer<T> setter, T value) {
//				if (Objects.nonNull(value)) {
//					setter.accept(value);
//				}
//			}
//
//			public static <T> void setIfNotNull(Consumer<T> setter, T value, T defaultValue) {
//				if (Objects.nonNull(value)) {
//					setter.accept(value);
//				} else {
//					setter.accept(defaultValue);
//				}
//			}
//			
//			public static String convertNumberToWords(int n) {
//				input = numToString(n);
//
//				String converted = "";
//
//				int pos = 1;
//				boolean hun = false;
//				while (input.length() > 0) {
//					if (pos == 1){ // TENS AND UNIT POSITION				
//						if (input.length() >= 2){ // TWO DIGIT NUMBERS					
//							String temp = input.substring(input.length() - 2,input.length());
//							input = input.substring(0, input.length() - 2);
//							converted += digits(temp);
//						} else if (input.length() == 1){ // 1 DIGIT NUMBER					
//							converted += digits(input);
//							input = "";
//						}
//						pos++;
//					} else if (pos == 2) { // HUNDRED POSITION				
//
//						String temp = input.substring(input.length() - 1,
//								input.length());
//						input = input.substring(0, input.length() - 1);
//						if (converted.length() > 0 && digits(temp) != "") {
//							converted = (digits(temp) + maxs[pos] + " and") + converted;
//							hun = true;
//						} else {
//							if (digits(temp) == "")
//								;
//							else
//								converted = (digits(temp) + maxs[pos]) + converted;
//							hun = true;
//						}
//						pos++;
//					} else if (pos > 2){ // REMAINING NUMBERS PAIRED BY TWO
//					
//						if (input.length() >= 2){ // EXTRACT 2 DIGITS
//
//							String temp = input.substring(input.length() - 2, input.length());
//							input = input.substring(0, input.length() - 2);
//							if (!hun && converted.length() > 0)
//								converted = digits(temp) + maxs[pos] + " and"
//										+ converted;
//							else {
//								if (digits(temp) == "")
//									;
//								else
//									converted = digits(temp) + maxs[pos] + converted;
//							}
//						} else if (input.length() == 1){// EXTRACT 1 DIGIT					
//
//							if (!hun && converted.length() > 0)
//								converted = digits(input) + maxs[pos] + " and"
//										+ converted;
//							else {
//								if (digits(input) == "")
//									;
//								else
//									converted = digits(input) + maxs[pos] + converted;
//								input = "";
//							}
//						}
//						pos++;
//					}
//				}
//				/*return converted + " Only/-";*/
//				return converted ;
//			}
//			
//			private static String digits(String temp){ // TO RETURN SELECTED NUMBERS IN WORDS	    
//		        String converted="";
//		        
//		        for(int i=temp.length()-1;i >= 0;i--){
//		           int ch=temp.charAt(i)-48;
//		            
//		           if(i==0	&&	ch>1 && temp.length()> 1)
//		            converted=tens[ch-2]+converted; // IF TENS DIGIT STARTS WITH 2 OR MORE IT FALLS UNDER TENS
//		            
//		           else if(i==0&&ch==1&&temp.length()==2) {// IF TENS DIGIT STARTS WITH 1 IT FALLS UNDER TEENS	            
//		                int sum=0;
//		                for(int j=0;j < 2;j++)
//		                sum=(sum*10)+(temp.charAt(j)-48);
//		                return teen[sum-10];
//		            }
//		            else{
//		                if(ch > 0)
//		                converted = units[ch] + converted;
//		            } // IF SINGLE DIGIT PROVIDED
//		        }
//		        return converted;
//		    }
//			
//			 private static String numToString(int x){ // CONVERT THE NUMBER TO STRING	    	
//			        String num="";
//			        while(x!=0) {
//			            num=((char)((x%10)+48))+num;
//			            x/=10;
//			        }
//			       return num;
//			    }
//			 
//			 public static Integer str2Int(String str) {
//					Integer result = null;
//					if (null == str || 0 == str.length()) {
//						return null;
//					}
//					try {
//						result = Integer.parseInt(str);
//					} catch (NumberFormatException e) {
//						String negativeMode = "";
//						if (str.indexOf('-') != -1)
//							negativeMode = "-";
//						str = str.replaceAll("-", "");
//						if (str.indexOf('.') != -1) {
//							str = str.substring(0, str.indexOf('.'));
//							if (str.length() == 0) {
//								return (Integer) 0;
//							}
//						}
//						String strNum = str.replaceAll("[^\\d]", "");
//						if (0 == strNum.length()) {
//							return null;
//						}
//						result = Integer.parseInt(negativeMode + strNum);
//					}
//					return result;
//				}
//			
//		}
//
//	
//
//		package com.octa.shopping.controller;
//
//		import java.io.IOException;
//		import java.util.ArrayList;
//		import java.util.List;
//		import java.util.Map;
//
//		import org.springframework.beans.factory.annotation.Autowired;
//		import org.springframework.http.HttpHeaders;
//		import org.springframework.http.HttpStatus;
//		import org.springframework.http.MediaType;
//		import org.springframework.http.ResponseEntity;
//		import org.springframework.web.bind.annotation.GetMapping;
//		import org.springframework.web.bind.annotation.PathVariable;
//		import org.springframework.web.bind.annotation.PostMapping;
//		import org.springframework.web.bind.annotation.PutMapping;
//		import org.springframework.web.bind.annotation.RequestBody;
//		import org.springframework.web.bind.annotation.RequestHeader;
//		import org.springframework.web.bind.annotation.RequestMapping;
//		import org.springframework.web.bind.annotation.RestController;
//		import org.springframework.web.reactive.function.BodyInserters;
//		import org.springframework.web.reactive.function.client.WebClient;
//
//		import com.fasterxml.jackson.core.JsonProcessingException;
//		import com.fasterxml.jackson.core.type.TypeReference;
//		import com.fasterxml.jackson.databind.JsonMappingException;
//		import com.fasterxml.jackson.databind.ObjectMapper;
//		import com.octa.shopping.VND.ShoppingDescription;
//		import com.octa.shopping.VND.ShoppingName;
//		import com.octa.shopping.VND.ShoppingVersion;
//		import com.octa.shopping.commonbean.CommonResponseBean;
//		import com.octa.shopping.jwt.service.IJwtTokenUtilService;
//		import com.octa.shopping.order.bean.OmsOrderResponseBean;
//		import com.octa.shopping.order.bean.OrderPlaceBean;
//		import com.octa.shopping.order.service.IOrderService;
//		import com.octa.shopping.orderpanel.bean.OrderDetailSearchBean;
//		import com.octa.shopping.returnorder.bean.ReturnSubOrderRequestBean;
//		import com.octa.shopping.service.impl.UserLoginService;
//		import com.octa.shopping.user.services.IUserLoginService;
//		import com.octa.shopping.utils.ShoppingUtils;
//
//		import lombok.extern.slf4j.Slf4j;
//		import reactor.core.publisher.Mono;
//
//		@ShoppingVersion("1.0.0")
//		@ShoppingName("OrderController")
//		@ShoppingDescription("OrderController.class")
//		@Slf4j
//		@RestController
//		@RequestMapping("/api/orderPanel")
//		public class OrderController {
//
//			private final WebClient webClient;
//			private final ObjectMapper objectMapper = new ObjectMapper();
//			@Autowired IUserLoginService iUserLoginService;
//			@Autowired UserLoginService userLoginService;
//			@Autowired IJwtTokenUtilService iJwtTokenUtilService;
//			@Autowired IOrderService iOrderService;
//
//			public OrderController(WebClient.Builder webClientBuilder) {
////				this.webClient = webClientBuilder.baseUrl("https://omsapi.octashop.com").build();
//				this.webClient = webClientBuilder.baseUrl("http://192.168.58.49:8080").build();
////				this.webClient = webClientBuilder.baseUrl("https://omsapi.proscale.quantstate.ai").build();
//			}
//
//			@PostMapping("/order-placed")
//			public ResponseEntity<CommonResponseBean> orderPlaced(@RequestHeader("Authorization") String token, @RequestBody OrderPlaceBean orderPlaceBean) {
//				log.info("Inside Order Placed Structure");
//				CommonResponseBean commonResponseBean = new CommonResponseBean();
//				try {
//					Long userIds = iJwtTokenUtilService.fetchUserIdFromToken(token);
//					orderPlaceBean = iOrderService.prepareOrderDetailBean(orderPlaceBean,userIds);
//					Mono<String> result = webClient.post().uri("/api/orderPlace/placeOrder")
//							.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//							.body(BodyInserters.fromValue(orderPlaceBean)).retrieve()
//							.onStatus(status -> status.is4xxClientError(), response -> {
//								response.bodyToMono(String.class).subscribe(body -> log.info("Error Response: " + body));
//								return Mono.error(new RuntimeException("4xx error"));
//							}).bodyToMono(String.class);
//					
//					String response = result.block();
//					log.info("Response data: " + response);
//
//					@SuppressWarnings("unchecked")
//					Map<String, Object> responseMap = objectMapper.readValue(response, Map.class);
//
//					commonResponseBean.setResponse(responseMap.get("response"));
//					commonResponseBean.setMessage((String) responseMap.get("message"));
//					commonResponseBean.setSuccess((Boolean) responseMap.get("success"));
//				} catch (Exception e) {
//					e.printStackTrace();
//					commonResponseBean.setResponse("Error occurred: " + e.getMessage());
//				}
//				return new ResponseEntity<>(commonResponseBean, HttpStatus.OK);
//			}
//			
//
//
//
//
//		package com.octa.shopping.utils;
//
//		import java.io.Serializable;
//		import java.security.Key;
//		import java.util.Base64;
//		import java.util.Date;
//		import java.util.HashMap;
//		import java.util.Map;
//		import java.util.function.Function;
//
//		import javax.crypto.Cipher;
//		import javax.crypto.KeyGenerator;
//		import javax.crypto.SecretKey;
//		import javax.crypto.spec.SecretKeySpec;
//
//		import org.springframework.beans.factory.annotation.Value;
//		import org.springframework.stereotype.Component;
//
//		import com.octa.shopping.jwt.service.IJwtTokenUtilService;
//
//		import io.jsonwebtoken.Claims;
//		import io.jsonwebtoken.Jwts;
//		import io.jsonwebtoken.SignatureAlgorithm;
//		import io.jsonwebtoken.security.Keys;
//
//		@Component
//		public class JwtTokenUtil implements Serializable, IJwtTokenUtilService {
//			 private static final long serialVersionUID = 1L;
//
//			    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
//			    private static final String AES_ALGORITHM = "AES";
//
//			    @Value("${server.servlet.session.timeout}")
//			    private Long sessionTimeout;
//
//			    // HMAC key for signing JWTs
//			    private final Key hmacKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//			    public static String secret = "9ez/uqcQLFEeIG2KQy8pelosVDUB175poEoqpHh4YIg=";
//
//			    
//			    public String getUsernameFromToken(String token) {
//			        return getClaimFromToken(token, Claims::getSubject);
//			    }
//
//			    public String getUserTypeFromToken(String token) {
//			        Claims claims = Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token).getBody();
//			        return (String) claims.get("userType");
//			    }
//			    
//			    public String getUserIdFromTokens(String token) {
//			        Claims claims = Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token).getBody();
//			        return (String) claims.get("userId");
//			    }
//
//			    public Date getExpirationDateFromToken(String token) {
//			        return getClaimFromToken(token, Claims::getExpiration);
//			    }
//
//			    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//			        final Claims claims = getAllClaimsFromToken(token);
//			        return claimsResolver.apply(claims);
//			    }
//
//			    public Claims getAllClaimsFromToken(String token) {
//			        return Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token).getBody();
//			    }
//
//			    @SuppressWarnings("unused")
//				private Boolean isTokenExpired(String token) {
//			        final Date expiration = getExpirationDateFromToken(token);
//			        return expiration.before(new Date());
//			    }
//
//			    public String generateTokens(String loginId, String userType) {
//			        Map<String, Object> claims = new HashMap<>();
//			        claims.put("userType", userType);
//			        claims.put("userId", loginId);
//
//			        return Jwts.builder()
//			                .setClaims(claims)
//			                .setSubject(loginId)
//			                .setIssuedAt(new Date(System.currentTimeMillis()))
//			                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//			                .signWith(hmacKey)
//			                .compact();
//			    }
//			    
//			    public Long fetchUserIdFromToken(String encryptedToken) {
//					Long id = null;
//					try {
//						encryptedToken = encryptedToken.substring(7);
//						String decrypt = decrypt(encryptedToken);
//						String  claims = getUsernameFromToken(decrypt);
//						if(claims != null) {
//							id = Long.parseLong(claims);
//						}
//					}catch(Exception e) {
//						e.printStackTrace();
//					}
//					return id;
//				}
//
//			    public String createToken(String subject, String claimKey, Object claimValue, long tokenValidityInMs, Boolean rememberMe) {
//			        Map<String, Object> claims = new HashMap<>();
//			        claims.put(claimKey, claimValue);
//			        long now = (new Date()).getTime();
//			        Date validity = new Date(now + tokenValidityInMs);
//			        return Jwts.builder()
//			                .setClaims(claims)
//			                .setSubject(subject)
//			                .setIssuedAt(new Date(System.currentTimeMillis()))
//			                .setExpiration(validity)
//			                .signWith(hmacKey)
//			                .compact();
//			    }
//
//			    public Claims parseToken(String token) {
//			        return Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(token).getBody();
//			    }
//
//			    public Claims getOtpJwtClaims(String token) {
//			        Claims claims = null;
//			        try {
//			            claims = getAllClaimsFromToken(token);
//			        } catch (Exception e) {
//			            e.printStackTrace();
//			        }
//			        return claims;
//			    }
//
//			    // AES encryption method
//			    public String encrypt(String data) throws Exception {
//			        SecretKeySpec key = new SecretKeySpec(Base64.getDecoder().decode(secret), AES_ALGORITHM); // Decode the Base64 key
//			        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
//			        cipher.init(Cipher.ENCRYPT_MODE, key);
//			        byte[] encryptedData = cipher.doFinal(data.getBytes());
//			        return Base64.getEncoder().encodeToString(encryptedData); // Ensure data is Base64 encoded
//			    }
//
//			    public String decrypt(String encryptedData) throws Exception {
//			        SecretKeySpec key = new SecretKeySpec(Base64.getDecoder().decode(secret), AES_ALGORITHM); // Decode the Base64 key
//			        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
//			        cipher.init(Cipher.DECRYPT_MODE, key);
//			        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
//			        byte[] decryptedData = cipher.doFinal(decodedData);
//			        return new String(decryptedData);
//			    }
//
//			    public static String generateSecretKey() throws Exception {
//			        KeyGenerator keyGen = KeyGenerator.getInstance(AES_ALGORITHM);
//			        keyGen.init(256); // for AES-256
//			        SecretKey secretKey = keyGen.generateKey();
//			        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
//			         
//			    }
//			}
//
//
//
//		
//		
//		package com.fnbs.digibank.mongoLog.entity;
//
//
//			import java.time.LocalDateTime;
//		import java.util.Map;
//
//			import org.springframework.data.mongodb.core.mapping.Document;
//
//			import lombok.AllArgsConstructor;
//			import lombok.Builder;
//			import lombok.Data;
//			import lombok.NoArgsConstructor;
//
//			@Document(collection = "debitcard_login_logs")
//			@Data
//			@NoArgsConstructor
//			@AllArgsConstructor
//			@Builder
//			public class DebitCardLoginLogs {
//
//			    private String browserDetails;
//			    private String OperatingSystem;
//			    private String ipAddress;
//			    private String requestUrl;    
////			    private String methodName;
//			    private Object emailObject;
//			    private Object requestPayload;
//			    private String timestamp;
//			    private Map<String , Object> responseData;
//			    
//			}
//
//
//		package com.fnbs.digibank.mongoLog;
//
//		import java.time.LocalDateTime;
//		import java.time.ZoneId;
//		import java.time.ZonedDateTime;
//		import java.util.Map;
//
//		import org.aspectj.lang.ProceedingJoinPoint;
//		import org.aspectj.lang.annotation.Around;
//		import org.aspectj.lang.annotation.Aspect;
//		import org.springframework.http.ResponseEntity;
//		import org.springframework.stereotype.Component;
//		import org.springframework.web.context.request.RequestContextHolder;
//		import org.springframework.web.context.request.ServletRequestAttributes;
//
//		import com.fasterxml.jackson.databind.ObjectMapper;
//		import com.fnbs.digibank.mongoLog.entity.DebitCardLoginLogs;
//		import com.fnbs.digibank.mongoLog.repository.DebitCardLogEntryRepository;
//		import com.fnbs.digibank.utils.CommonUtils;
//
//		import eu.bitwalker.useragentutils.UserAgent;
//		import jakarta.servlet.http.HttpServletRequest;
//		import lombok.RequiredArgsConstructor;
//		import lombok.extern.slf4j.Slf4j;
//
//		@Aspect
//		@Component
//		@RequiredArgsConstructor
//		@Slf4j
//		public class DebitLoginLogAspect {
//
//			private final DebitCardLogEntryRepository logRepository;
//			private final ObjectMapper objectMapper;
//
//			@SuppressWarnings("unchecked")
//			@Around("execution(* com.fnbs.digibank.userLogin.controller.UserLoginController.loginByDebitCardNo(..))")
//			public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
//
//				DebitCardLoginLogs.DebitCardLoginLogsBuilder logBuilder = DebitCardLoginLogs.builder();
//
//				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//						.getRequest();
//				String fullUrl = request.getRequestURL().toString();
//				String userAgentString = request.getHeader("User-Agent");
//
//				String ipAddress = request.getHeader("X-Forwarded-For");
//				if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
//					ipAddress = request.getRemoteAddr();
//				}
//
//				UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
//				String browser = userAgent.getBrowser().getName();
//
//				String OS = userAgent.getOperatingSystem().getName();
//				String version = userAgent.getBrowserVersion() != null ? userAgent.getBrowserVersion().getVersion() : "unknown";
//
//				ZonedDateTime indianTime = LocalDateTime.now().atZone(ZoneId.of("Asia/Kolkata")); // IST is Asia/Kolkata 
//				LocalDateTime localDateTime = indianTime.toLocalDateTime();
//
//				logBuilder.timestamp(localDateTime.toString()).requestUrl(fullUrl).OperatingSystem(OS).ipAddress(ipAddress)
////						.methodName(joinPoint.getSignature().getName())
//						.browserDetails(browser + "/ version:- " + version);
//
//				Object[] args = joinPoint.getArgs();
//				if (args.length > 0) {
//					try {
//						Map<String, Object> jsonPayload = objectMapper.convertValue(args[0], Map.class);
//						logBuilder.requestPayload(jsonPayload);
//					} catch (Exception e) {
//						log.warn("Failed to serialize request payload: {}", e.getMessage());
//						logBuilder.requestPayload("Unable to serialize request payload");
//					}
//				}
//
//				Object result = joinPoint.proceed();
//
//				if (result instanceof ResponseEntity<?> responseEntity) {
//					Object responseBody = responseEntity.getBody();
//
//					Map<String, Object> mailInfo =null;
//					
//					try {
//						Map<String, Object> responseMap = objectMapper.convertValue(responseBody, Map.class);
//						
//						if (responseMap.containsKey("mailInfo")) {
//							
//							 mailInfo = (Map<String, Object>) responseMap.get("mailInfo");
//						      responseMap.remove("mailInfo");
//						
//						}
//						
//						if (CommonUtils.isNotEmpty(mailInfo)) {
//							mailInfo.remove("msg");
//							mailInfo.remove("otp");
//						}
//						
//						logBuilder.emailObject(mailInfo);
//						logBuilder.responseData(responseMap);
//					} catch (Exception e) {
//						log.warn("Failed to serialize response body: {}", e.getMessage());
//						logBuilder.responseData(Map.of("error", "Unable to serialize response"));
//					}
//				} else {
//					logBuilder.responseData(Map.of("rawResponse", result != null ? result.toString() : "null"));
//				}
//
//				logRepository.save(logBuilder.build());
//
//				return result;
//			}
//		}
//
//
//		
//		package com.fnbs.digibank.mongoLog.repository;
//
//		import org.springframework.data.mongodb.repository.MongoRepository;
//
//		import com.fnbs.digibank.mongoLog.entity.DebitCardLoginLogs;
//		import com.fnbs.digibank.mongoLog.entity.DocumentUploadLog;
//
//		public interface DebitCardLogEntryRepository extends MongoRepository<DebitCardLoginLogs, Long> {
//
//		}
//
//
//
//		package com.fnbs.digibank.userLogin.controller;
//
//		import java.util.LinkedHashMap;
//		import java.util.Map;
//
//		import org.springframework.beans.factory.annotation.Autowired;
//		import org.springframework.http.HttpStatus;
//		import org.springframework.http.ResponseEntity;
//		import org.springframework.validation.BindingResult;
//		import org.springframework.web.bind.annotation.GetMapping;
//		import org.springframework.web.bind.annotation.PostMapping;
//		import org.springframework.web.bind.annotation.RequestBody;
//		import org.springframework.web.bind.annotation.RequestHeader;
//		import org.springframework.web.bind.annotation.RequestMapping;
//		import org.springframework.web.bind.annotation.RestController;
//
//		import com.fnbs.digibank.dto.AccountLoginDTO;
//		import com.fnbs.digibank.dto.DebitCardLoginDTO;
//		import com.fnbs.digibank.dto.EmailLoginDTO;
//		import com.fnbs.digibank.userLogin.service.UserLoginService;
//		import com.fnbs.digibank.utils.CommonUtils;
//		import com.fnbs.digibank.utils.ValidationUtils;
//
//		import jakarta.validation.Valid;
//		   
//
//		@RestController
//		@RequestMapping("/userLogin")
//		public class UserLoginController {
//
//			@Autowired private UserLoginService loginService; 
//		@PostMapping("/debit/login")
//			public ResponseEntity<Object> loginByDebitCardNo(@Valid @RequestBody DebitCardLoginDTO loginDTO,BindingResult bindingResult) {
//				if (bindingResult.hasErrors()) {
//					return ValidationUtils.getValidationErrorResponse(bindingResult);
//				}
//				try {
//					Map<String, Object> dataMap = loginService.loginByDebitCard(loginDTO.getDebitCardNo(), loginDTO.getPin());
//					if (!dataMap.containsKey("message")) {
//						String token = loginService.generateJwtToken(dataMap.get("emailId").toString());
//						dataMap.remove("emailId");
//						dataMap.put("message", "User login successful");
//						dataMap.put("accBalance", dataMap.get("accBalance"));
//						dataMap.put("token", token);
//						return ResponseEntity.ok(dataMap);
//					}
//					return ResponseEntity.badRequest().body(dataMap);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return ResponseEntity.badRequest().body(Map.of("message", "Failed to login"));
//			}
//		}
//
//		
//		
//		
//		
	}

}
