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
    <div class="card mb-3 mt-3">
        <h1 class="card-header">Unsere Snacks und Getränke</h1>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-6 mb-2">
                    <h5 class="card-title">Erhältlich bei uns im Kino</h5>
                    <div class="table-responsive">
                        <table class=" tableSnacks">
                            <tr>
                                <td colspan="2"><h5>Popcorn</h5></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <h7>wahlweise süß oder salzig</h7>
                                </td>
                            </tr>
                            <tr>
                                <td>Popcorn klein</td>
                                <td class="text-right">3,50 €</td>
                            </tr>
                            <tr>
                                <td>Popcorn groß</td>
                                <td class="text-right">5,50 €</td>
                            </tr>
                            <tr>
                                <td colspan="2"><h5>Nachos</h5></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <h7>wahlweise mit Käse- oder Salsasauce</h7>
                                </td>
                            </tr>
                            <tr>
                                <td>Nachos klein</td>
                                <td class="text-right">3,50 €</td>
                            </tr>
                            <tr>
                                <td>Nachos groß</td>
                                <td class="text-right">5,50 €</td>
                            </tr>
                            <tr>
                                <td colspan="2"><h5>Sonstige Snacks</h5></td>
                            </tr>
                            <tr>
                                <td>M&M's</td>
                                <td class="text-right">3,50 €</td>
                            </tr>
                            <tr>
                                <td>Chips</td>
                                <td class="text-right">1,80 €</td>
                            </tr>
                            <tr>
                                <td>Ben&Jerry's</td>
                                <td class="text-right">2,00 €</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="col-lg-6 mb-2">
                    <div class="table-responsive">
                        <table class="tableSnacks">
                            <tr>
                                <td colspan="3"><h5>Alkoholfreie Getränke</h5></td>
                            </tr>
                            <tr>
                                <td>Stilles Wasser</td>
                                <td class="text-right">0,5 l</td>
                                <td class="text-right">2,00 €</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="text-right">1,0 l</td>
                                <td class="text-right">3,00 €</td>
                            </tr>
                            <tr>
                                <td>Mineralwasser</td>
                                <td class="text-right">0,5 l</td>
                                <td class="text-right">2,00 €</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="text-right">1,0 l</td>
                                <td class="text-right">3,00 €</td>
                            </tr>
                            <tr>
                                <td>Cola/Fanta/Sprite/Spezi</td>
                                <td class="text-right">0,5 l</td>
                                <td class="text-right">2,50 €</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="text-right">1,0 l</td>
                                <td class="text-right">3,50 €</td>
                            </tr>
                            <tr>
                                <td>Eistee Lemon/Pfirsich</td>
                                <td class="text-right">0,5 l</td>
                                <td class="text-right">2,50 €</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="text-right">1,0 l</td>
                                <td class="text-right">3,50 €</td>
                            </tr>
                            <tr>
                                <td colspan="3"><h5>Alkoholische Getränke</h5></td>
                            </tr>
                            <tr>
                                <td>Welde Bier</td>
                                <td class="text-right">0,33 l</td>
                                <td class="text-right">3,00 €</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="text-right">0,5 l</td>
                                <td class="text-right">3,50 €</td>
                            </tr>
                            <tr>
                                <td>Welde Naturadler</td>
                                <td class="text-right">0,33 l</td>
                                <td class="text-right">3,00 €</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="text-right">0,5 l</td>
                                <td class="text-right">3,50 €</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="card-deck mb-3">
        <div class="card">
            <img class="card-img-top" src="img/snacks/popcorn.png" alt="popcorn">
            <div class="card-body">
                <h5 class="card-title">Popcorn Sparmenü</h5>
                <p class="card-text">Eine große Portion Popocorn & ein Getränk*</p>
                <p class="card-text"><small class="text-muted">*Softdrink 1,0 l <i>oder</i> Bier 0,5
                    l</small>
                </p>
                <a href="#" class="btn btn-primary">6,99€</a>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top" src="img/snacks/nachos.png" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Nachos Sparmenü</h5>
                <p class="card-text">Eine große Portion Nachos & ein Getränk*</p>
                <p class="card-text"><small class="text-muted">*Softdrink 1,0 l <i>oder</i> Bier 0,5
                    l</small>
                </p>
                <a href="#" class="btn btn-primary">6,99€</a>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top" src="img/snacks/paerchenmenue.png" alt="pärchenmenü">
            <div class="card-body">
                <h5 class="card-title">Pärchenmenü</h5>
                <p class="card-text">Eine große Portion Nachos (2 Saucen) <i>oder</i> eine große Portion
                    Popcorn
                    & zwei Getränke* </p>
                <p class="card-text"><small class="text-muted">*Softdrink 1,0 l <i>oder</i> Bier 0,5 l
                </small>
                </p>
                <a class="btn btn-primary">11,00€</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="elements/footer.jsp"/>
</body>
</html>
