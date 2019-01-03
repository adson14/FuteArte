package itext;

import connection.ConnectionFactory;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.text.Element;
import model.bean.Aluno;

import model.bean.Funcionario;
import model.bean.Mensalidade;
import model.bean.Turma;
import model.dao.AlunoDAO;
import model.dao.FuncionarioDAO;
import model.dao.MensalidadeDAO;
import model.dao.TurmaDAO;

/**
 *
 * @author Aluno
 */
public class relatorio {

    double toti;

    public void gerarPDFTurma(int codTurma, Object nTurma) {
        ConnectionFactory c = new ConnectionFactory();
        Document doc = new Document();
        TurmaDAO t = new TurmaDAO();
        AlunoDAO A = new AlunoDAO();
        Aluno An = new Aluno();

        List<Aluno> listA = A.listarAlunoTurma(codTurma);

        String pdfTurma = "relatorioTurmas.pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(pdfTurma));
            doc.open();

            Paragraph p = new Paragraph("Relação de Alunos da Turma: " + nTurma + "\n\n");

            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph("");
            doc.add(p);

            PdfPTable table = new PdfPTable(3);//numeor de colunas

            PdfPCell cel1 = new PdfPCell(new Paragraph("TURMA"));
            PdfPCell cel2 = new PdfPCell(new Paragraph("NOME"));
            PdfPCell cel3 = new PdfPCell(new Paragraph("IDADE"));

            table.addCell(cel1);
            table.addCell(cel2);
            table.addCell(cel3);

            for (Aluno aluno : listA) {

                cel1 = new PdfPCell(new Paragraph(aluno.getNomeTurma()));
                cel2 = new PdfPCell(new Paragraph(aluno.getNome()));
                cel3 = new PdfPCell(new Paragraph(aluno.getIdade() + ""));

                table.addCell(cel1);
                table.addCell(cel2);
                table.addCell(cel3);
            }

            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File(pdfTurma));

        } catch (Exception e) {
        }

    }

    public void gerarPDMensalidade(int codTurma, Object nTurma) {
        ConnectionFactory c = new ConnectionFactory();
        Document doc = new Document();
        TurmaDAO t = new TurmaDAO();
        AlunoDAO A = new AlunoDAO();
        Aluno An = new Aluno();
        Mensalidade m = new Mensalidade();
        MensalidadeDAO md = new MensalidadeDAO();

        List<Mensalidade> listM = md.listarMensalidadeTurma(codTurma);
        List<Mensalidade> listT = md.listarTotalMensalidade(codTurma);

        String pdfMensalidade = "relatorioMensalidade.pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(pdfMensalidade));
            doc.open();

            Paragraph p = new Paragraph("Relação de Mensalidades da Turma: " + nTurma + "\n\n");

            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph("");
            doc.add(p);

            PdfPTable table = new PdfPTable(3);//numeor de colunas
            PdfPTable table1 = new PdfPTable(3);//

            PdfPCell cel1 = new PdfPCell(new Paragraph("NOME"));
            PdfPCell cel2 = new PdfPCell(new Paragraph("VENCIMENTO"));
            PdfPCell cel3 = new PdfPCell(new Paragraph("VALOR"));
            PdfPCell cel4 = new PdfPCell(new Paragraph("TOTAL"));
            //PdfPCell cel4 = new PdfPCell(new Paragraph("TOTAL"));

            table.addCell(cel1);
            table.addCell(cel2);
            table.addCell(cel3);
            table1.addCell("");
            table1.addCell("");
            table1.addCell("");

            for (Mensalidade mensalidade : listM) {

                cel1 = new PdfPCell(new Paragraph(mensalidade.getNomeAluno()));
                cel2 = new PdfPCell(new Paragraph(mensalidade.getDataVencimento()));
                cel3 = new PdfPCell(new Paragraph(mensalidade.getValor() + ""));

                table.addCell(cel1);
                table.addCell(cel2);
                table.addCell(cel3);

            }

            for (Mensalidade mensalidade0 : listT) {

                cel1 = new PdfPCell(new Paragraph("--------------------"));
                cel2 = new PdfPCell(new Paragraph("--------------------"));
                cel3 = new PdfPCell(new Paragraph("--------------------"));

                table1.addCell(cel1);
                table1.addCell(cel2);
                table1.addCell(cel3);

            }

            for (Mensalidade mensalidade1 : listT) {

                cel1 = new PdfPCell(new Paragraph(""));
                cel2 = new PdfPCell(new Paragraph("TOTAL--->"));
                cel3 = new PdfPCell(new Paragraph(mensalidade1.getTotal() + ""));

                table1.addCell(cel1);
                table1.addCell(cel2);
                table1.addCell(cel3);

            }

            doc.add(table);
            doc.add(table1);
            doc.close();
            Desktop.getDesktop().open(new File(pdfMensalidade));

        } catch (Exception e) {
        }

    }
}
