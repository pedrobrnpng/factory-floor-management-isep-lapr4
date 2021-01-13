package eapli.base.app.backoffice.console.presentation.errornotificationmanagement;

import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.framework.visitor.Visitor;

public class ProductionLinePrinter implements Visitor<ProductionLine> {

    @Override
    public void visit(ProductionLine visitee) {
        System.out.printf("%s", visitee.toStringSimple());
    }
    
}