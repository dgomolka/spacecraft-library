package com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.mapper

import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.TypePresentationModel
import com.psycodeinteractive.spacecraftlibrary.ui.contract.mapper.PresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.TypeUiModel

class TypePresentationToUiMapper : PresentationToUiMapper<TypePresentationModel, TypeUiModel>() {
    override fun map(input: TypePresentationModel) = TypeUiModel(
        id = input.id,
        name = input.name
    )
}
