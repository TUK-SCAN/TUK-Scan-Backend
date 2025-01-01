package com.tukscan.tukscan.security.application.usecase;

import com.tukscan.tukscan.core.annotation.bean.UseCase;
import com.tukscan.tukscan.security.application.dto.request.ReadSerialIdAndProviderRequestDto;
import com.tukscan.tukscan.security.application.dto.response.ReadSerialIdAndProviderResponseDto;

@UseCase
public interface ReadSerialIdAndProviderUseCase {

    ReadSerialIdAndProviderResponseDto execute(ReadSerialIdAndProviderRequestDto requestDto);
}
