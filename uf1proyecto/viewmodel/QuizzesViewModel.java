package com.example.gerard.uf1proyecto.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.gerard.uf1proyecto.HttpUtils;
import com.example.gerard.uf1proyecto.QuizzesDao;
import com.example.gerard.uf1proyecto.QuizzesDatabase;
import com.example.gerard.uf1proyecto.model.Answer;
import com.example.gerard.uf1proyecto.model.Question;
import com.example.gerard.uf1proyecto.model.Quiz;
import com.example.gerard.uf1proyecto.model.Repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class QuizzesViewModel extends AndroidViewModel {
    private final MutableLiveData<Repository> repository = new MutableLiveData<>();
    private final QuizzesDao quizzesDao;

    public QuizzesViewModel(Application application){
        super(application);

        quizzesDao = QuizzesDatabase.getInstance(this.getApplication()).getQuizzesDao();
    }

    public LiveData<List<Repository>> getRepositories(){
        return quizzesDao.getRepositories();
    }

    public LiveData<List<Quiz>> getQuizzes(long repositoryId){
        return quizzesDao.getQuizzes(repositoryId);
    }

    public LiveData<List<Question>> getQuestions(long quizId){
        return quizzesDao.getQuestions(quizId);
    }

    public LiveData<List<Answer>> getAnswers(long questionId){
        return quizzesDao.getAnswers(questionId);
    }

    public MutableLiveData<Boolean> downloadRepository(String url){
        MutableLiveData<Boolean> dowloaded = new MutableLiveData<>();

        new RepositoryDownloadTask(dowloaded).execute(url);

        return dowloaded;
    }

    class RepositoryDownloadTask extends AsyncTask<String, Void, Void> {
        DuplicatedRepositoryException duplicatedRepositoryException;
        MutableLiveData<Boolean> downloaded;

        RepositoryDownloadTask(MutableLiveData<Boolean> downloaded){
            this.downloaded = downloaded;
        }

        @Override
        protected Void doInBackground(String... urls) {
            try {
                processJSON(HttpUtils.get(urls[0]), urls[0]);
            } catch (DuplicatedRepositoryException e){
                duplicatedRepositoryException = e;
            } catch (JSONException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (duplicatedRepositoryException == null){
                downloaded.postValue(true);
            } else {
                downloaded.postValue(false);
            }
        }
    }

    public class DuplicatedRepositoryException extends Exception {
        public DuplicatedRepositoryException(String message) {
            super(message);
        }
    }

    private void processJSON(String jsonResponse, String url) throws DuplicatedRepositoryException, JSONException {
        try {
            JSONObject repoJSON = new JSONObject(jsonResponse);

            Repository repository = new Repository();
            repository.name = repoJSON.getString("name");
            repository.url = url;
            repository.imageUrl = repoJSON.getString("imageUrl");

            Repository checkDupe = quizzesDao.getRepository(repository.name);
            if(checkDupe != null){
                throw new DuplicatedRepositoryException(repository.name);
            } else {
            }

            long repositoryId = quizzesDao.insertRepository(repository);

            JSONArray quizzesJSON = repoJSON.getJSONArray("quizzes");
            for(int i=0; i<quizzesJSON.length(); i++){
                JSONObject quizJSON = quizzesJSON.getJSONObject(i);

                Quiz quiz = new Quiz();
                quiz.name = quizJSON.getString("name");
                quiz.repositoryId = repositoryId;

                long quizId = quizzesDao.insertQuiz(quiz);

                JSONArray questionsJSON = quizJSON.getJSONArray("questions");
                for(int j=0; j<questionsJSON.length(); j++){

                    JSONObject questionJSON = questionsJSON.getJSONObject(j);

                    Question question = new Question();
                    question.question = questionJSON.getString("question");
                    question.correct = questionJSON.getInt("correct");
                    question.quizId = quizId;

                    long questionId = quizzesDao.insertQuestion(question);

                    JSONArray answersJSON = questionJSON.getJSONArray("answers");
                    for(int k=0; k<answersJSON.length(); k++){
                        Answer answer = new Answer();
                        answer.answer = answersJSON.getString(k);
                        answer.questionId = questionId;

                        quizzesDao.insertAnswer(answer);
                    }
                }
            }
        } catch (JSONException e) {
            throw e;
        }
    }
}
