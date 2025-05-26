package org.example.diameter.handlers.Gx.mappers;

import org.example.diameter.packet.messages.CreditControlAnswer;
import org.example.diameter.packet.messages.CreditControlRequest;
import org.example.pcc.contexts.SessionInitContext;
import org.example.pcc.dto.SessionPolicy;

public interface GxToPccHttpMapper {
    SessionInitContext fromCcrInit(CreditControlRequest ccr);
    CreditControlAnswer toCca(SessionPolicy sessionPolicy);
}
