/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagesmanagement.application;

import eapli.base.factoryfloormanagementarea.domain.InternalCode;
import eapli.base.factoryfloormanagementarea.domain.Machine;
import eapli.base.factoryfloormanagementarea.repository.MachineRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.messagesmanagement.domain.EndOfActivityMessage;
import eapli.base.messagesmanagement.domain.Message;
import eapli.base.productionordermanagement.domain.ProductionOrder;
import eapli.base.productionordermanagement.repositories.ProductionOrderRepository;
import eapli.framework.domain.model.general.Description;
import eapli.framework.domain.model.general.Designation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Utilizador
 */
public class EndOfActivityMessageFactory implements MessageFactory {

    @Override
    public Message createMessage(List<String> attributes) {
        final MachineRepository repositoryMachine = PersistenceContext.repositories().machine();

        final ProductionOrderRepository repositoryProductionOrder = PersistenceContext.repositories().productionOrder();
        final Machine machine = repositoryMachine.ofIdentity(new InternalCode(attributes.get(0).trim())).orElseThrow(() -> new IllegalStateException("Machine doesn't exist"));
        final Description type = Description.valueOf(attributes.get(1).trim());
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        final LocalDateTime dateHour = LocalDateTime.parse(attributes.get(2).trim(), formatter);
        ProductionOrder productionOrder=null;
        if (attributes.size() ==4) {
            productionOrder = repositoryProductionOrder.ofIdentity(Designation.valueOf(attributes.get(3).trim())).orElseThrow(() -> new IllegalStateException("Production order doesn't exist"));
        }
        return new EndOfActivityMessage(machine, type, dateHour, productionOrder);
    }

}
