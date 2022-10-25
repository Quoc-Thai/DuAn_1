/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import JPanel.*;
import JavaClass.DanhMucChucNang;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class ChuyenManHinh {

    private List<DanhMucChucNang> ListItem = null;
    private JPanel jpnRoot;
    private String kindSelected = "";

    public ChuyenManHinh(JPanel jpnRoot) {
        this.jpnRoot = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "TrangChu";
        jpnItem.setBackground(Color.red);
        jlbItem.setBackground(Color.red);

        jpnRoot.removeAll();
        jpnRoot.setLayout(new BorderLayout());
        jpnRoot.add(new TrangChuJPanel());
        jpnRoot.validate();
        jpnRoot.repaint();
    }

    public void setEvent(List<DanhMucChucNang> ListItem) {
        this.ListItem = ListItem;
        for (DanhMucChucNang item : ListItem) {
            item.getJpn().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
            item.getJpn().addMouseMotionListener(new LabelMotionEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    class LabelMotionEvent implements MouseMotionListener {

        private JPanel node, jpnItem;
        private String kind;
        private JLabel jlbItem;

        public LabelMotionEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        public void mouseDragged(MouseEvent e) {

        }

        public void mouseMoved(MouseEvent e) {
            jpnItem.setBackground(Color.red);
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node, jpnItem;
        private String kind;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent event) {
            switch (kind) {
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "QuanNhan":
                    node = new QuanLyQuanNhanJPanel();
                    break;
                case "ChucNangQuanNhan":
                    node = new QuanLyQuanNhanJPanel();
                    break;
                case "QuanPhuc":
                    node = new QuanLyQuanPhucJPanel();
                    break;
                case "ChucNangQuanPhuc":
                    node = new QuanLyQuanPhucJPanel();
                    break;
            }
            jpnRoot.removeAll();
            jpnRoot.setLayout(new BorderLayout());
            jpnRoot.add(node);
            jpnRoot.validate();
            jpnRoot.repaint();
            SetChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(Color.green);
            jlbItem.setBackground(Color.white);
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(Color.red);
            jlbItem.setBackground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(Color.green);
                jlbItem.setBackground(Color.yellow);
            }
        }
    }

    private void SetChangeBackground(String kind) {
        for (DanhMucChucNang item : ListItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getJpn().setBackground(Color.red);
                item.getJlb().setBackground(Color.white);
            } else {
                item.getJpn().setBackground(Color.green);
                item.getJlb().setBackground(Color.yellow);
            }
        }
    }

}
