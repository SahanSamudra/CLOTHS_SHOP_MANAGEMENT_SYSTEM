package tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemTm {
    private String id;
    private String type;
    private double price;
    private int qty;
    private String supID;
    private String size;
}
