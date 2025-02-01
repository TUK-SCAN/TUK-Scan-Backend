package com.tookscan.tookscan.order.application.usecase;

import com.tookscan.tookscan.core.annotation.bean.UseCase;
import java.util.List;

@UseCase
public interface DeleteAdminDocumentsUseCase {
    void execute(List<Long> documentIds);
}
