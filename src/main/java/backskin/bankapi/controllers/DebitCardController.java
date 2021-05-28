package backskin.bankapi.controllers;

import backskin.bankapi.presentation.DebitCardInfo;
import backskin.bankapi.services.DebitCardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Debit Cards Controller.
 */
@RestController
@RequestMapping("/api/v1/debitcards")
public class DebitCardController {

    private final DebitCardService debitCardService;

    /**
     * Instantiates a new Debit card controller.
     *
     * @param debitCardService the debit card service
     */
    public DebitCardController(DebitCardService debitCardService) {
        this.debitCardService = debitCardService;
    }

    /**
     * Gets all cards info.
     *
     * @return the all cards info
     */
    @GetMapping({"","all"})
    List<DebitCardInfo> getAllCardsInfo()  {
        try {
            return debitCardService.getAllCardsInfo();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return new ArrayList<>();
    }
}
