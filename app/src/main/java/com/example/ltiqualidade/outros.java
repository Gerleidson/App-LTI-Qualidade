package com.example.ltiqualidade;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.InputFilter;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
public class outros extends AppCompatActivity {
    private EditText nomeEditText;
    private EditText enderecoEditText;
    private EditText bairroEditText;
    private EditText cidadeEditText;
    private Spinner cpfSpinner;
    private EditText cpfEditText;
    private TextView urgenteEditText;
    private TextView contatoTextView;
    private TextView anotacaoTextView;
    private Spinner formaPagamentoSpinner;
    private CheckBox checkBoxEletrico;
    private CheckBox checkBoxHidraulico;
    private CheckBox checkBoxPintura;
    private CheckBox checkBoxOutros;
    private EditText contatoEditText;
    private boolean isEletricoSelected = false;
    private boolean isHidraulicoSelected = false;
    private boolean isPinturaSelected = false;
    private boolean isOutrosSelected = false;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //tema com fundo branco
        setTheme(R.style.theme_main);

        setContentView(R.layout.activity_outros);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Inicialização dos elementos do layout
        nomeEditText = findViewById(R.id.nomeEditText);
        enderecoEditText = findViewById(R.id.enderecoEditText);
        cidadeEditText = findViewById(R.id.cidadeEditText);
        bairroEditText = findViewById(R.id.bairroEditText);
        cpfSpinner = findViewById(R.id.spinner5);
        cpfEditText = findViewById(R.id.cpfEditText);
        urgenteEditText = findViewById(R.id.urgenteEditText);
        contatoTextView = findViewById(R.id.contato);
        anotacaoTextView = findViewById(R.id.anotacao);
        formaPagamentoSpinner = findViewById(R.id.spinner6);
        checkBoxEletrico = findViewById(R.id.checkBoxEletrico);
        checkBoxHidraulico = findViewById(R.id.checkBoxHidraulico);
        checkBoxPintura = findViewById(R.id.checkBoxPintura);
        checkBoxOutros = findViewById(R.id.checkBoxOutros);

        // Configuração dos spinners
        List<String> tipoDocumentos = new ArrayList<>();
        tipoDocumentos.add("CPF");
        tipoDocumentos.add("CNPJ");
        ArrayAdapter<String> tipoDocumentosAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipoDocumentos);
        cpfSpinner.setAdapter(tipoDocumentosAdapter);

        // Definindo limite de caractere para item selecionado no spinner
        cpfSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItem = adapterView.getItemAtPosition(position).toString();

                if (selectedItem.equals("CPF")) {
                    cpfEditText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(11) });
                } else if (selectedItem.equals("CNPJ")) {
                    cpfEditText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(14) });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Implemente conforme necessário
            }
        });

        List<String> formasPagamento = new ArrayList<>();
        formasPagamento.add("Dinheiro");
        formasPagamento.add("PIX");
        formasPagamento.add("Cartão de crédito");
        formasPagamento.add("Cartão de débito");
        ArrayAdapter<String> formasPagamentoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, formasPagamento);
        formaPagamentoSpinner.setAdapter(formasPagamentoAdapter);

        // Configuração do botão de compartilhamento
        FloatingActionButton shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(v -> compartilharDados());

        // Configuração dos checkboxes
        checkBoxEletrico.setOnCheckedChangeListener((buttonView, isChecked) -> isEletricoSelected = isChecked);

        checkBoxHidraulico.setOnCheckedChangeListener((buttonView, isChecked) -> isHidraulicoSelected = isChecked);

        checkBoxPintura.setOnCheckedChangeListener((buttonView, isChecked) -> isPinturaSelected = isChecked);

        checkBoxOutros.setOnCheckedChangeListener((buttonView, isChecked) -> isOutrosSelected = isChecked);
        // Limita o campo contato para 11 caracteres
        contatoEditText = findViewById(R.id.contato);
        contatoEditText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(11) });
    }
    @SuppressLint("QueryPermissionsNeeded")
    private void compartilharDados() {
        String nome = nomeEditText.getText().toString();
        String endereco = enderecoEditText.getText().toString();
        String cidade = cidadeEditText.getText().toString();
        String bairro = bairroEditText.getText().toString();
        String tipoDocumento = cpfSpinner.getSelectedItem().toString();
        String cpf = cpfEditText.getText().toString();
        String urgente = urgenteEditText.getText().toString();
        String anotacao = anotacaoTextView.getText().toString();
        String formaPagamento = formaPagamentoSpinner.getSelectedItem().toString();

        // Obtém o valor do contato
        String contato = contatoTextView.getText().toString();

        // Remove todos os caracteres que não sejam dígitos
        String numeroFormatado = PhoneNumberUtils.stripSeparators(contato);

        // Formata o número no padrão "(xx) xxxxx-xxxx"
        String numeroFormatadoExibicao = PhoneNumberUtils.formatNumber(numeroFormatado, "BR");

        // Verifica se o número foi formatado corretamente
        if (numeroFormatadoExibicao == null) {
            Toast.makeText(this, "Contato inválido. Insira um número de telefone válido.", Toast.LENGTH_SHORT).show();
            return;
        }
        // Atualiza o valor da variável contato com o número formatado
        contato = numeroFormatadoExibicao;

        StringBuilder mensagemBuilder = new StringBuilder();
        mensagemBuilder.append("Agendamento para serviços:\n")
                .append("Nome: ").append(nome).append("\n")
                .append("Endereço: ").append(endereco).append("\n")
                .append("Bairro: ").append(bairro).append("\n")
                .append("Cidade: ").append(cidade).append("\n")
                .append(tipoDocumento).append(": ").append(cpf).append("\n")
                .append("Urgente? ").append(urgente).append("\n")
                .append("Contato: ").append(contato).append("\n")
                .append("Anotação: ").append(anotacao).append("\n")
                .append("Forma de pagamento: ").append(formaPagamento).append("\n");

        if (isEletricoSelected) {
            mensagemBuilder.append("Serviço selecionado: Elétrico\n");
        }
        if (isHidraulicoSelected) {
            mensagemBuilder.append("Serviço selecionado: Hidráulico\n");
        }
        if (isPinturaSelected) {
            mensagemBuilder.append("Serviço selecionado: Pintura\n");
        }
        if (isOutrosSelected) {
            mensagemBuilder.append("Serviço selecionado: Outros\n");
        }

        String mensagem = mensagemBuilder.toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);

        Intent chooser = Intent.createChooser(intent, "Compartilhar via");
        if (chooser.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        } else {
            Toast.makeText(this, "Nenhum aplicativo de compartilhamento disponível.", Toast.LENGTH_SHORT).show();
        }
    }
}
