package com.mirai.importback.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirai.importback.controllers.ProductListController;
import com.mirai.importback.entities.ProductList;
import com.mirai.importback.entities.StoreProducts;
import com.mirai.importback.entities.Users;
import com.mirai.importback.services.impl.ProductListImpl;
import com.mirai.importback.services.impl.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = ProductListController.class)
@ActiveProfiles("test")
public class ProductListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductListImpl productListService;
    private List<ProductList> productListList;

    @BeforeEach
    void setUp(){
        productListList = new ArrayList<>();
        productListList.add(new ProductList(1L,"IPHONE","https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcT9wpDgBk6aeJ5bSrcFsGgX7-dDw1rZLO-ExAib9OCpalV3m4hG5v53v-x7FrIdlp0gbtQDvkBwfw&usqp=CAc","238",new StoreProducts()));
        productListList.add(new ProductList(2L,"TABLET","data:image/png;base64,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    ","300",new StoreProducts()));
        productListList.add(new ProductList(3L,"WATCH","data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIUAAACwCAMAAAAWs10QAAAAIVBMVEUBAQoAAAAAAQkBAQpHcEwAAggCAwoCBAoCBAoFBwYCBAkJ/aGCAAAAC3RSTlPI/NO9AN6okHAoSqr9n48AABADSURBVHic7ZzpduMgEoVRYdb3f+ChNigQ2HLSZ2Z+hHQn3U5ifbq1UGxyr/+H5v7XANT+KEb7oxjtj2K0P4rR/gVFKaXWmqXVXGsp/z2KgpdO8dBSro9ZfkhR6nx5T3/4C38oyiOSH1CUmuare/lDTb/K/1p7API1BSPoxeL4LCRdj8ETU/2nFCXRReTS9raHFP1VZ7/9nuMbiprkIovqBoD/7UCaA+cEJr6zy3OKOl971+gnAIJtBNNInEu/pyhGaPyY9B62cMBXdtxA/ssc/miWhxTJGQTn9WPRARnaFftr/NPAr7o3cjyiqPyO0lQLKwjbol1NafvP0t/O4ffe8YQiyduN5t1qkEgu6bolxDfHK6ySc/lnFMXP12eLTCaJkeOix8bclIPl2FnlI0UFtzSvOLb1SwbowREgWBAyGP7m3SqfKLYQTNBBokKEQBCGREJVONQqN4wPFPkG0Q1jo4MDUhlCMP/SnAHGKrCG7HsKhCAXg0WJ/gcpWiIAMgAEMCz6B3ruQjkYY/HRtxRWiUkUyQaqRMEfXTmGTSyHxsrso+8oqigBEwOJYEwS+caKDwAwHKOzqIW6c/CX+JCCHBNuOszJAntw+fnENxwMgv0cZt+YMM4UNRgZjF+MZMEwUMZvgFpl4bgZZcE4UhiIyRy3EDEWLiyGjdmgXkpfFGNV40RBPgHgbpE696nRSCEYQTi6r3YGNYoPK8aBoooO4hp3xxgc0++V7qLWMNCTh01fJlL2FJlC4+aZfviF2mTtnarmyilwu4tIzeOuOW9sKTL3TNxL3qRQx3Q3g2wwIBhphk3g4kJEfn1HkUOHWJvJxn6JEG0paK9mFBlRM1yD3sOdKGIY7zJ6557Le11JIJvf9+MXw10LLQc5XCXE1ncp2XO9ZAZYS/NaXga30+L1MgzjVgwL2wRvA8WoN4oagS6d7q291l7HzylKmXuF5q6bijZNNoTulmqYHickRlwoaotgRNAxJl8bWxsU8x/+HnIQRdiWThGMhxoYyWeBLOsdixHxPgxFRKdPMvinj13jYTKWFA3ian6yqZzCLIZ1UhieAcMzOkVpdTpdJuWDPVSY2G6HpWjh1uJ1hShholg4+N9OxWAHV4oCEa+uI/EmlAYj2cYBwUUXrnbx9heVRYxNOZvDitFl6KHnerSSfypFuDAFAd+uv+JFstCtY1CkC/Mm2qtmAiEIjpO1cIrBOsTAkERibBJZjKwUHu+wXcTjjbaWrqkF83/Iub3gezF1i9bZLUfLRUNEKCBrlDBFe9/LZyCp2x2nRuEmn5yZanNLLLsphcFcsJjufWRffim/TPawGEKBd++bGDhN5VDsZpHrTWvFR5ThAJncJo2KV+Dy0ghBo1UZMXTPCAnYPYkiNgjXGFy/jIfUcwY6yIqRIUZKLjgmc1P3ThQOVg8d43d9gQuYTlHQNS/DQO7Hgz4UZtPQYb1m0eYaxjNykNR4U2NANPeg0iEYiub1zRO2V9u39qPgObHSuGyamRCK1TVujX6EswFRYGB4/xyC1ZAshjZxtvjMXNs2M4UNQ5i/jBgpcNXmjl+1UHPMnOeIIt60cFB34ToiRiMF056jYVXJIvTTlkNtWigFODN3VoUiVO4+9xzSu43cmSCW8qUW+aLESp0siTEco6gWPlsxzOB5DO2pemxJz2HGzaWkK3wjRutoWrcXB4XJ4lo2A5XaYK8+N5B5SOrNfKilfCfFBQlqso5h3DNpzw3H64/yleeYHA5zC1J84RUXOUaqXQtYR2ju5g9O5rnMt7iix15IKKprf79yz8tqAVP3HjUx3vKDeuuYd+P6xLU6D0rJFZdavtHCUKC8NnuWAIsazmqxDLWSUPjmnI2hFPf56oYCE0br/QC7lMaSrRiuX9WtV11H35koMlK0KCkYKI+NkpiiVWGNwCW872zEcDDmHPpYBm48VOoIRSQKCtcvGlPEkFLroHHK0/TvCdQJbhedG2b631AEpmjXh0jF6VQHB6u/GezeB70OdhRPTZIjeWdonVZyvHITyiTG5Bl375AIkUj9oRbVIUXEIlyXZ2wZPF1x7xBiER4VZZ+U4ovOBJJHChwA6ZLAtPCQRo4aMtjPooST3LlQfLYIVSKxVTmNAnuJVvrxfDQYxyiLJY5zyI7HZt9qUbBMvRJkpgghOlkRATvojeqYH9yCQvVrLQoOXhpF6BRepv5a+jIUtSesSYplhgwF9N9r0SDIh5XiwnGRFprTWHFvgkUI7I/dneKDFAqhFA6HiT5wjrqmuYy4XHXnGaTFhuItByYE0Uso/MUDeC4lLISa5E329ExBtdZE8VYKA6EUkcbMjiuXefBejt6gDFRoNS1uFE8h2liOe3YSQzBec4sLw30a2bNFqubOpH3qWY3wKq8xaGmFFvXsTQytLNdJnXS2hUghWhBFDCFHqi88X21b7cwQrTnu2T3O6MQ2rn2trb5PFt0vaP4it5okoR5SfLY+boMBDQIm80h9ER3NcG2mt8pmbWUWg6ZtlcJhtQc5izHyBgOzooVonZlSeHKK3YyjrmMcfZMt4hNRJPUJHKpvMRBidpgapO6MvLq+o1gzxuoUqoWhqNfwzBXDN3MsXmspaHixmQM+uadXg1gtMFJTHwfQ5zRhbCAeafE+SIYW0VKIPa4VI9I8yweK20Jta/mdRYwWM8XQomMAQ6wMKwVstahne0xa+JMWigHNHDuIq16TFm6nxX2N3oCoFjjdctMiWAykqFsI5LAUfqvFrq5Z3EJnGqUf6Vp0jpibx/p6mnL8bJEC/oYh16cvuk7etdgPUn0blx8YWqkRP8VIWbXws1fMfhGFIsyegVZxp84tvvK3FOtmna6F596stq5se9PuPEIJX2sxpixG/h4U2CumjV9ggwPDtXjnPlLnoVGQHTHDOKtfpMPkxTMK+KyFbJeSXRNGihvF6hXvKPosSmxd+wVxmy8MBIeSw7H9Eqo7LRaOI8XUm6HFN71Z7Q5JFxeK1O0hSWvuzXbF3nF2x1B4mlJ6pwWtjmKljhApql/Qnh5LkUWLFeSBFpEn3jda9N6MF+NcwDWvhGKMGJkoykGLM8XwTtq/t6eQDWa81tIoeCnSL1pkyVq1z138wC9oUmsXI6pF5MVJH2gdQf1zaMEUsJRapj3xi3SKVJHe8cJkcwyGiHHkb3JTpsDBvzA89gtfeu5M7kwhbsFLKX33gDotB6qMilrHeRoQnbNWk/czBWvftyvLMvWqRe0WKftB2ZGiXIPijUWGG2JoyGYG6xetS5VxKvWpvDCxohz9olzliV+I8pQmcJI44pqKxGm3CFG4hNPg+y78jRaW4hgjXpIWTZcHXrNPxi3AyWo/UqRa9os0DymaFm+ylk+S6P1wTtOZ+Zni51rsKbQfoRViSuDeRogTLVaKO8lzv9hQ9J7dp9GZRe3XqS8D6lItxU6NZ1ocMviYYKNdFVRdaPpmLYC7kX/gnQ612G2Meb1GCc6lEPi+U5w9Anj7BVHE8zrRM4qTFnaICGbjvvapZBCdB7/q1/1IMBaJRy3m2Rwtfrs9aLsCpgvSwmGf+r1FYqc4adHnUTYNvxPIQ4taJOWyv94TCrLILkZ2o/Y+RMU9SuSuOEPDFDkX91XWCobCn2qtd3MHTkJX9661iggp9jsfjn5RO0UMNGbfUqSjSbzT7Vqyktkoaj7MuJ61gEFBw+UdxMvvxsvykh7zyELhWsXyJcWVRpXD+wK3FOE8Cc2brPqexupCKb7sJwjOVU4241RIe4p6XsoMIkUkt3avghTxQHFebYZqR8t7CjpmsJt7Bdn3QJtyXrLaj4vs32oRsfpNsscy3PcUYqNVK90HYda7deOtN3tdPe4C+TZfXCkTRWIKiDuKsYOSV/tlB0AIuutXnJP3ooTaAuVLiswUbJCwPadTQBbU5v2ewsARwhFO+3LwXfdLume/KDrKoGnwdV8jNR2PuHVTitjGq1sgRcXtaYee5Lx2hCGi48N98fvy0OfjDUf3k+EWvF8rXG86903zuTlnzF59M8AuRArlxpEveIuUWVrubqF71zBjHMJkp0OL7khjLbTH1dxi55wZpkk9IREdxDsl5eo+vnzB49X+2hJ+8EjRN4bv3ML3mU2FAF/N1gfv+1kHklIWAqwaAS+wN0fzIHi1gYXXXeHH2tcvnZknK1m3sKcVRAOcx5DuhAaNmdwFPcbzVt8LE3epzYC5jb95r+R1yhZsEMsBWon2CNGemN1K7poumj1dLOUcL5oIpVEsEuL0PBKFgntdKUJpVOluy4f0zjOCw40RyexJ6em7U/RlCd4lVFytfOdVjNDyGvoipkwkwT2oMnS47fuVVmGFgKhu2bXIEwXmT+FIeO9KU/jAfC0uV+qK23cpoYLjUVS4oDv63OI87Q0jNIZz9tJIAx2cODzeesIEnStdn9slV+qO03eE+/t5PjayXuzGQR/eeXMaqKcbGtfL+WwexYwtobQ3Fl/vu4CDbkr3t03p3DLMcbpsFCIpRsIdSc9fTg8C6Mf+yQG4qN0PXW7OCYi2m2b26tDN9p82qTdr+cP7r9N8ViLJR05+7MkDtz3GsjvLuZZd3p5Usx1Aq8PDqAKcDuim89rOuj7+xLawuIWprXRUC8M/d0O0G4X3gI6dePyOSuH1aDaea3TbszQ9Yx21mH1zc7qp8I6KZV/qvJ2Z3zbsz92qFDclhhYksA2tTZeczjt151KFRD9K4VeOOVd469Wn82Y9SvUk3zjGZ3bLbk963QLkLoVfssz+Xu4nDfSvm26KR9w3MdfcvbqFW6Q4nUNct7UrA8ybwPxuNXdatLM9xxQg0+8dzmTeMAaHuS0/Bb22/QB58Yo5wE/nUy2GuzOMmu32Bsf9Dt0p3O0Q0PGs7s43bhtH/eZoqJsrG9f7MPtLi4Lnc8sH33DdOfQdp98qJyVsfNwEfHOG++4bhsSUKtN9LYXNdP0O/tXZ/vTAKMt73vqO0Q0YJW4e/f5s/+4cyhIo3uSMYjbNzavbkwnvtcCH5xzsjtEBzFr0DFRu5gBD0JXYnZ98SyFqBCuEidzOQdFf7fXvnqk/vCvYPz7/YsEYPOsYaxQ24IYKMFyUlQi7UuDjs0AKmDOdd+9QW8e45O3hlN0xadJ5W488eDqL76dKl3Cx8bqEhrm49oHsE/tH5jx5RoyGyq6nNT5qFBhJ3rqPO1RmD5+XU3bHtXSuanDMCjjop/IV4vC0nMfPDsobDJiyOWNoDEkwjQoaRzjnByk9f47SqoLN5pMctsex33jzcKvnz5TixMj3DzaFziCuu8RQARn+0ZOt8Mw9d9pqCaMG6BWncHF90HB+nNTXFPissXtQziNx52Qbkh8Inxh+9Ny16X5h/ccyf9Pah6ed/YiCnkHXB2qnVZixrSA9eRTeT5/Hl9P9AWg8pDWD2pg/y/AbCkbhRxNOJP1RcN88mvBXFJ3GPqaR5oC+fEzjP6H4ffujGO2PYrQ/itH+KEb7D5RbFY+KQ/0HAAAAAElFTkSuQmCC","429",new StoreProducts()));
        productListList.add(new ProductList(4L,"SUIT","https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcRie4Ky5KTFvx8ueu8tQD1fLdBM1MfrShZq1w3lKSiiZrC9uPKeeyUVW09vht4meUCJRWHWjzBXnWo&usqp=CAc","123",new StoreProducts()));
        productListList.add(new ProductList(5L,"SHOES","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRiPgaFaPA5BJwg4J38_NCgO_gdk3u0ynJdIcD262uCdQ&s","222",new StoreProducts()));

    }

    @Test
    void findAllProductListTest() throws Exception{
        given(productListService.getAll()).willReturn(productListList);
        mockMvc.perform(get("/api/productList")).andExpect(status().isOk());
    }

    @Test
    void findProductListByIdTest() throws Exception{
        Long productListId = 1L;
        ProductList productList = new ProductList(1L,"IPHONE","https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcT9wpDgBk6aeJ5bSrcFsGgX7-dDw1rZLO-ExAib9OCpalV3m4hG5v53v-x7FrIdlp0gbtQDvkBwfw&usqp=CAc","238",new StoreProducts());

        given(productListService.getById(productListId)).willReturn(Optional.of(productList));
        mockMvc.perform(get("/api/productList/{id}", productListId)).andExpect(status().isOk());
    }

    @Test
    void insertProductListTest() throws Exception{
        ProductList productList =new ProductList(1L,"IPHONE","https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcT9wpDgBk6aeJ5bSrcFsGgX7-dDw1rZLO-ExAib9OCpalV3m4hG5v53v-x7FrIdlp0gbtQDvkBwfw&usqp=CAc","238",new StoreProducts());

        mockMvc.perform(post("/api/productList")
                        .content(asJsonString(productList))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCustomerTest() throws Exception{
        Long id = 1L;
        ProductList productList = new ProductList(1L,"IPHONE","https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcT9wpDgBk6aeJ5bSrcFsGgX7-dDw1rZLO-ExAib9OCpalV3m4hG5v53v-x7FrIdlp0gbtQDvkBwfw&usqp=CAc","238",new StoreProducts());

        given(productListService.getById(id)).willReturn(Optional.of(productList));
        mockMvc.perform(put("/api/productList/{id}", id)
                        .content(asJsonString(productList))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCustomerTest() throws Exception{
        Long id = 1L;
        ProductList productList = new ProductList(1L,"IPHONE","https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcT9wpDgBk6aeJ5bSrcFsGgX7-dDw1rZLO-ExAib9OCpalV3m4hG5v53v-x7FrIdlp0gbtQDvkBwfw&usqp=CAc","238",new StoreProducts());

        given(productListService.getById(id)).willReturn(Optional.of(productList));
        mockMvc.perform(delete("/api/productList/{id}", id))
                .andExpect(status().isOk());
    }
    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
