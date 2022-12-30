package com.seinoindomobil.jetepod.data.mapper

import com.seinoindomobil.jetepod.data.remote.dto.login.LoginDTO
import com.seinoindomobil.jetepod.domain.model.Login

fun LoginDTO.toLoginDomain(): Login {
    return Login(
        id = result.data.id,
        active = result.data.active,
        activated = result.data.activated,
        lock = result.data.lock,
        division = result.data.division,
        department = result.data.department,
        section = result.data.section,
        unit = result.data.unit,
        area = result.data.area,
        email = result.data.email,
        phone = result.data.phone,
        gender = result.data.gender,
        name = result.data.name,
        company = result.data.company,
        token = result.signature.access_token,
        refresh_token = result.signature.refresh_token
    )
}