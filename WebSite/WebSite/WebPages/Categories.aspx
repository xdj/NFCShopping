﻿<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="Categories.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.Categories" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
        <script type="text/javascript">
            $(function () {
                $(".children:eq(0)").show();
                $("span:eq(0)").html("-");
                $("a:eq(3)").css({ "color": "red" });
                $(".head:eq(0)").toggle(function () {
                    $(this).next().hide();
                    $("span:eq(0)").html("+");
                }, function () {
                    $(this).next().show();
                    $("span:eq(0)").html("-");
                });
            });
    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ObjectDataSource ID="CategoriesDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Category"
        DeleteMethod="DeleteCategory" InsertMethod="InsertCategory" SelectMethod="GetCategories"
        TypeName="NFCShoppingWebSite.BLL.CategoryBL" UpdateMethod="UpdateCategory">
        <UpdateParameters>
            <asp:Parameter Name="category" Type="Object" />
            <asp:Parameter Name="origCategory" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
  <h1 style=" color:Black;"><b>商品分类</b></h1>
    <asp:ListView ID="CategoriesListView" runat="server" DataSourceID="CategoriesDataSource"
        GroupItemCount="1">
        <EmptyDataTemplate>
            没有任何分类数据。</EmptyDataTemplate>
        <ItemSeparatorTemplate>
        </ItemSeparatorTemplate>
        <ItemTemplate>
            <table border="0" width="300">
                <asp:Label ID="CategoryLabel" runat="server" Text='<%# Eval("categoryName") %>' Font-Size="Large"
                    Font-Bold="true" ForeColor="Black">
                </asp:Label>
            </table>
            <asp:ListView ID="SecCategoriesListView" runat="server" DataSource='<%# Eval("SecCategories") %>'>
                <EmptyDataTemplate>
                    没有任何子分类数据。
                </EmptyDataTemplate>
                <ItemSeparatorTemplate>
                </ItemSeparatorTemplate>
                <ItemTemplate>
                   <a href='<%# VirtualPathUtility.ToAbsolute("~/WebPages/Products.aspx?secCategoryID=" + Eval("secCategoryID")) %>'>
                      <asp:Label ID="SecCategoryLabel" runat="server" Text='<%# Eval("secCategoryName") %>'
                            Font-Size="Medium" ForeColor="Black"></asp:Label>
                    </a>
                    </br>
                </ItemTemplate>
            </asp:ListView>
        </ItemTemplate>
    </asp:ListView>
</asp:Content>
