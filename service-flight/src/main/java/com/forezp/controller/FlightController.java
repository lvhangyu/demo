package com.forezp.controller;
import com.forezp.mvc.ResultModel;
import com.forezp.pojo.dto.FlightDTO;
import com.forezp.pojo.vo.FlightVO;
import com.forezp.service.FlightService;
import com.forezp.service.OrderService;
import com.forezp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName FlightController
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 10:15
 * Version 1.0
 */
@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultModel<FlightVO> save(
            @RequestBody FlightDTO flightDTO,
            HttpServletRequest request, HttpServletResponse response) throws ParseException {
        FlightVO flightVO = flightService.save(flightDTO);
        return new ResultModel<FlightVO>().setCode(HttpStatus.OK.value()).setMsg("success").setData(flightVO);
    }

    @DeleteMapping("/delete")
    public ResultModel delete(
            @RequestParam Long id,
            HttpServletRequest request, HttpServletResponse response) throws ParseException {
        flightService.delete(id);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }

    @PostMapping("/update")
    public ResultModel<FlightVO> update(
//            @RequestParam(required = true, value = "id") Long id,
//            @RequestParam(required = false, value = "flightDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date flightDate,
//            @RequestParam(required = false, value = "aviationCorp") String aviationCorp,
//            @RequestParam(required = false, value = "departureAddr") String departureAddr,
//            @RequestParam(required = false, value = "arrivalAddr") String arrivalAddr,
//            @RequestParam(required = false, value = "departureTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date departureTime,
//            @RequestParam(required = false, value = "arrivalTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date arrivalTime,
//            @RequestParam(required = false, value = "luggage") Integer luggage,
//            @RequestParam(required = false, value = "fuelSurcharge") BigDecimal fuelSurcharge,
//            @RequestParam(required = false, value = "airportTax") BigDecimal airportTax,
//            @RequestParam(required = false, value = "remainingTicket") Integer remainingTicket,
//            @RequestParam(required = false, value = "ticketPrice") BigDecimal ticketPrice,
//            @RequestParam(required = false, value = "cabinType") Integer cabinType,
//            @RequestParam(required = false, value = "discounted_ticket_price") BigDecimal discounted_ticket_price,
//            @RequestParam(required = false, value = "discount") String discount,
            @RequestBody FlightDTO flightDTO,
            HttpServletRequest request, HttpServletResponse response) throws ParseException {
//        FlightDTO flightDTO = new FlightDTO();
//        flightDTO.setId(id)
//                .setFlightDate(flightDate)
//                .setAviationCorp(aviationCorp)
//                .setDepartureAddr(departureAddr)
//                .setArrivalAddr(arrivalAddr)
//                .setDepartureTime(departureTime)
//                .setArrivalTime(arrivalTime).setLuggage(luggage)
//                .setFuelSurcharge(fuelSurcharge)
//                .setAirportTax(airportTax)
//                .setRemainingTicket(remainingTicket)
//                .setTicketPrice(ticketPrice)
//                .setCabinType(cabinType)
//                .setDiscountedTicketPrice(discounted_ticket_price)
//                .setDiscount(discount);
        System.out.println(flightDTO.toString());
        FlightVO flightVO = flightService.update(flightDTO);
        System.out.println(flightVO.getFlightDate());
        return new ResultModel<FlightVO>().setCode(HttpStatus.OK.value()).setMsg("success").setData(flightVO);
    }

    @GetMapping("/query")
    public ResultModel<List<FlightVO>> query(
            HttpServletRequest request, HttpServletResponse response) throws ParseException {
        List<FlightVO> flightVOList = flightService.query();
        return new ResultModel<List<FlightVO>>().setCode(HttpStatus.OK.value()).setMsg("success").setData(flightVOList);
    }

    @GetMapping("/info")
    public ResultModel<FlightVO> info(
            @RequestParam(required = true, value = "id") Long id,
            HttpServletRequest request, HttpServletResponse response) throws ParseException {
        FlightVO flightVO = flightService.info(id);
        return new ResultModel<FlightVO>().setCode(HttpStatus.OK.value()).setMsg("success").setData(flightVO);
    }

    @GetMapping("/search")
    public ResultModel<List<FlightVO>> search(
            @RequestParam(required = false, value = "flightDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date flightDate,
            @RequestParam(required = false, value = "aviationCorp") String aviationCorp,
            @RequestParam(required = false, value = "departureAddr") String departureAddr,
            @RequestParam(required = false, value = "arrivalAddr") String arrivalAddr,
            @RequestParam(required = false, value = "departureTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date departureTime,
            @RequestParam(required = false, value = "arrivalTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date arrivalTime,
            @RequestParam(required = false, value = "ticketPrice") BigDecimal ticketPrice,
            @RequestParam(required = false, value = "cabinType") Integer cabinType,
            HttpServletRequest request, HttpServletResponse response) throws ParseException {
        List<FlightVO> flightVOList = flightService.serach(flightDate, aviationCorp, departureAddr, arrivalAddr, departureTime, arrivalTime, ticketPrice, cabinType);
        return new ResultModel<List<FlightVO>>().setCode(HttpStatus.OK.value()).setMsg("success").setData(flightVOList);
    }

    /**
     * 特价机票
     * @param request
     * @param response
     * @return
     * @throws ParseException
     */
    @GetMapping("/discountedTicket")
    public ResultModel<List<FlightVO>> discountedTicket(
            HttpServletRequest request, HttpServletResponse response) throws ParseException {
        List<FlightVO> flightVOList = flightService.discountedTicket();
        return new ResultModel<List<FlightVO>>().setCode(HttpStatus.OK.value()).setMsg("success").setData(flightVOList);
    }


    public static void main(String[] args) throws ParseException {
        String date1 = "2022-03-30 18:49:00";
        String date2 = "2022-03-30 18:22:00";
        String date = DateUtil.getDurationByDateStr(date1,date2,DateUtil.dateFormat[1]);
        System.out.println(date);
    }
}
