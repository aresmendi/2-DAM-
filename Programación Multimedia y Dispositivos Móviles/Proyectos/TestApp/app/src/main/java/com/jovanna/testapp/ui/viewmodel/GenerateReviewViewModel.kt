package com.jovanna.testapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jovanna.testapp.data.SimulatorRepository
import com.jovanna.testapp.model.ErrorTopic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GenerateReviewViewModel : ViewModel() {

    // Estado: Lista de temas de error
    private val _topics = MutableStateFlow<List<ErrorTopic>>(emptyList())
    val topics: StateFlow<List<ErrorTopic>> = _topics.asStateFlow()

    init {
        loadTopics()
    }

    private fun loadTopics() {
        _topics.value = SimulatorRepository.getErrorTopics()
    }

    // Función para marcar/desmarcar un tema
    fun toggleTopicSelection(topicId: String) {
        _topics.update { currentList ->
            currentList.map { topic ->
                if (topic.id == topicId) {
                    topic.copy(isSelected = !topic.isSelected)
                } else {
                    topic
                }
            }
        }
    }
}