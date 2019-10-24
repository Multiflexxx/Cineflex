<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="elements/head.jsp"/>
<body class="d-flex flex-column h-100">
<jsp:include page="elements/header.jsp"/>
<jsp:include page="locationPicker.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="search.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <div class="card">
        <h1 class="card-header">Unsere Snacks und Getränke</h1>
        <div class="card-body">
            <h5 class="card-title">Erhältlich bei uns im Kino</h5>
                <table align="center">
                    <td><h5>Popcorn</h5>
                        <h7>wahlweise süß oder salzig</h7>
                        <table class="tableSnacks">
                            <tr>
                                <td width="200px">Popcorn klein</td>
                                <td width="20px">3,50€</td>
                            </tr>
                            <tr>
                                <td>Popcorn groß</td>
                                <td>5,50€</td>
                            </tr>
                        </table>
                        <h5>Nachos</h5>
                        <h7>wahlweise mit Käse- oder Salsasauce</h7>
                        <table class="tableSnacks">
                            <tr>
                                <td width="200px">Nachos klein</td>
                                <td width="20px">3,50€</td>
                            </tr>
                            <tr>
                                <td>Nachos groß</td>
                                <td>5,50€</td>
                            </tr>
                        </table>
                        <h5>Sonstige Snacks</h5>
                        <table class="tableSnacks">
                            <tr>
                                <td width="200px">M&M's</td>
                                <td width="20px">3,50€</td>
                            </tr>
                            <tr>
                                <td>Chips</td>
                                <td>1,80€</td>
                            </tr>
                            <tr>
                                <td>Ben&Jerry's (3 Sorten)</td>
                                <td>2,00€</td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <h5>Alkoholfreie Getränke</h5>
                        <table class="tableSnacks">
                            <tr>
                                <td width="150px">Stilles Wasser</td>
                                <td width="10px">0,5 l</td>
                                <td width="10px">2,00€</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>1,0 l</td>
                                <td>3,00€</td>
                            </tr>
                            <tr>
                                <td>Mineralwasser</td>
                                <td>0,5 l</td>
                                <td>2,00€</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>1,0 l</td>
                                <td>3,00€</td>
                            </tr>
                            <tr>
                                <td>Cola/Fanta/Sprite/Spezi</td>
                                <td>0,5 l</td>
                                <td>2,50€</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>1,0 l</td>
                                <td>3,50€</td>
                            </tr>
                            <tr>
                                <td>Eistee Lemon/Pfirsich</td>
                                <td>0,5 l</td>
                                <td>2,50€</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>1,0 l</td>
                                <td>3,50€</td>
                            </tr>
                        </table>
                        <h5>Alkoholische Getränke</h5>
                        <table class="tableSnacks">
                            <tr>
                                <td width="330px">Welde Bier</td>
                                <td width="10px">0,33 l</td>
                                <td width="10px">3,00€</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>0,5 l</td>
                                <td>3,50€</td>
                            </tr>
                            <tr>
                                <td>Welde Naturadler</td>
                                <td>0,33 l</td>
                                <td>3,00€</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>0,5 l</td>
                                <td>3,50€</td>
                            </tr>
                        </table>
                    </td>
                </table>
            </div>
        </div>
    <div class="card">
        <h1 class="card-header">Unsere Sparmenüs</h1>
        <div class="card-body">
    <div class="card-deck">
        <div class="card">
            <img class="card-img-top" src="img/snacks/popcorn.png" alt="popcorn">
            <div class="card-body">
                <h5 class="card-title">Popcorn Sparmenü</h5>
                <p class="card-text">Eine große Portion Popocorn & ein Getränk*</p>
                <p class="card-text"><small class="text-muted">*Softdrink 1,0 l <i>oder</i> Bier 0,5 l</small></p>
                <a href="#" class="btn btn-primary">6,99€</a>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top" src="img/snacks/nachos.png" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Nachos Sparmenü</h5>
                <p class="card-text">Eine große Portion Nachos & ein Getränk*</p>
                <p class="card-text"><small class="text-muted">*Softdrink 1,0 l <i>oder</i> Bier 0,5 l</small></p>
                <a href="#" class="btn btn-primary">6,99€</a>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top" src="img/snacks/paerchenmenue.png" alt="pärchenmenü">
            <div class="card-body">
                <h5 class="card-title">Pärchenmenü</h5>
                <p class="card-text">Eine große Portion Nachos (2 Saucen) <i>oder</i> eine große Portion Popcorn & zwei Getränke*  </p>
                <p class="card-text"><small class="text-muted">*Softdrink 1,0 l <i>oder</i> Bier 0,5 l </small></p>
                <a class="btn btn-primary">11,00€</a>
            </div>
        </div>
    </div>
        </div>
    </div>
</div>
<jsp:include page="elements/footer.jsp"/>
</body>
</html>
